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

  def findById(name: String): Future[Option[Product]] = db.run(Products.filter(_.name === name).result.headOption)

  def update(name: String, product: Product): Future[Unit] = {
    val productToUpdate: Product = product.copy(name)
    db.run(Products.filter(_.name === name).update(productToUpdate)).map(_ => ())
  }

  private class ProductsTable(tag: Tag) extends Table[Product](tag, "PRODUCT") {

    def name = column[String]("NAME", O.PrimaryKey)

    def description = column[String]("DESCRIPTION")

    def price = column[Int]("PRICE")

    def category=column[String]("CATEGORY")

    def * = (name, description, price, category) <>(Product.tupled, Product.unapply _)
  }




}
