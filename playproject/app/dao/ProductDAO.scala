package dao

import scala.concurrent.Future

import javax.inject.Inject
import models.Product
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile



class ProductDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  private val Products = TableQuery[ProductsTable]

  def all(): Future[Seq[Product]] = db.run(Products.result)

  def insert(product: Product): Future[Unit] = db.run(Products += product).map { _ => () }

  def delete(name: String): Future[Unit] = db.run(Products.filter(_.name === name).delete).map(_ => ())

  def findById(id: Int): Future[Option[Product]] = db.run(Products returning Products.map(_.id)) += product)

  def update(name: String, product: Product): Future[Unit] = {
    val productToUpdate: Product = product.copy(name)
    db.run(Products.filter(_.name === name).update(productToUpdate)).map(_ => ())
  }

  def findByName(text: String): Future[Seq[Product]] =
  {
    db.run(Products.filter(_.name like s"%$text%").result)
  }

  def findByCategory(text:String):Future[Seq[Product]] =
  {
    db.run(Products.filter(_.category like s"%$text%").result)

   }

  private class ProductsTable(tag: Tag) extends Table[Product](tag, "PRODUCT") {

    def id = column[Int]("ID", O.PrimaryKey,O.AutoInc)
    
    def name = column[String]("NAME")

    def description = column[String]("DESCRIPTION")

    def price = column[Int]("PRICE")

    def category=column[String]("CATEGORY")

    def * = (id.?, name, description, price, category) <>(Product.tupled, Product.unapply _)
  }




}
