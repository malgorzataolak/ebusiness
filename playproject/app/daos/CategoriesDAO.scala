package daos

import javax.inject.Inject

import models.{Categories, CategoriesREST}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}


class CategoriesDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  val Categories = TableQuery[CategoriesTable]

  def all(implicit ec: ExecutionContext): Future[List[CategoriesREST]] = {
    val query =  Categories
    val results = query.result
    val futureCategories = db.run(results)
    futureCategories.map(
      _.map {
        a => CategoriesREST(id = a.id, name = a.name)
      }.toList)
  }

  def insert(category: Categories): Future[Unit] = db.run(Categories += category).map { _ => () }

  class CategoriesTable(tag: Tag) extends Table[Categories](tag, "Categories") {
    def id = column[Int]("id", O.AutoInc, O.AutoInc)
    def name = column[String]("name")
    def * = (id, name) <> (models.Categories.tupled, models.Categories.unapply)
  }

}
