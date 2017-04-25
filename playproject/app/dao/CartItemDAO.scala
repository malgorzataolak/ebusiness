package dao

import scala.concurrent.Future

import javax.inject.Inject
import models.CartItem
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile



class CartItemDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  private val CartItems = TableQuery[CartItemsTable]

  def all(): Future[Seq[CartItem]] = db.run(CartItems.result)

  def insert(cartItem: CartItem): Future[Unit] = db.run(CartItems += cartItem).map { _ => () }

 }

  private class CartItemsTable(tag: Tag) extends Table[CartItem](tag, "CART ITEM") {

    /*

    def name = column[String]("NAME", O.PrimaryKey)

    def description = column[String]("DESCRIPTION")

    def price = column[Int]("PRICE")

    def category=column[String]("CATEGORY")

    def * = (name, description, price) <>(Product.tupled, Product.unapply _)
    */
  }




