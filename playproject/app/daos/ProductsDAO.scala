package daos

import javax.inject.Inject

import models.{Products, ProductsREST}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}


class ProductsDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  val Products = TableQuery[ProductsTable]

  def all(implicit ec: ExecutionContext): Future[List[ProductsREST]] = {
    val query =  Products
    val results = query.result
    val futureProducts = db.run(results)
    futureProducts.map(
      _.map {
        a => ProductsREST(id = a.id, categoryID = a.categoryID, name = a.name,
         description = a.description, price = a.price)
      }.toList)
  }

  def getByCategory(categoryID: Int): Future[List[ProductsREST]] = {
    val futureProducts = db.run(Products.filter(_.categoryID === categoryID).result)
    futureProducts.map(
      _.map {
        a => ProductsREST(id = a.id, categoryID = a.categoryID, name = a.name,
         description = a.description, price = a.price)
      }.toList)
  }

  def insert(product: Products): Future[Unit] = db.run(Products += product).map { _ => () }

  class ProductsTable(tag: Tag) extends Table[Products](tag, "Products") {
    def id = column[Int]("id", O.AutoInc, O.AutoInc)
    def categoryID = column[Int]("categoryID")
    def name = column[String]("name")
    def description = column[String]("description")
    def price = column[Float]("price")
    def * = (id, categoryID, name, description, price) <> (models.Products.tupled, models.Products.unapply)
  }

}
