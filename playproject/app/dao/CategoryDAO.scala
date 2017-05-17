package dao


import scala.concurrent.Future

import javax.inject.Inject
import models.Category
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile



class CategoryDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  private val Categories = TableQuery[CategoriesTable]

  def all(): Future[Seq[Category]] = db.run(Categories.result)

  def insert(category: Category): Future[Unit] = db.run(Categories += category).map { _ => () }



  private class CategoriesTable(tag: Tag) extends Table[Product](tag, "PRODUCT") {
   

    def name = column[String]("NAME", O.PrimaryKey)


    def * = (name, description, price) <>(Product.tupled, Product.unapply _)
   
  }




}

