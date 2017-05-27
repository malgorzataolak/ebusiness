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

    def cartID=column[Int]("CART ITEM ID", O.AutoInc, O.AutoInc)
    def name=column[String]("NAME", O.PrimaryKey)
    def quantity=column[Int]("QUANTITY")
    def cartID=column[Int]("CART ID")
    
    def * =(cartItemID, name, quantity)<>(CartItem.tupled, CartItem.unapply _)
   
  }





