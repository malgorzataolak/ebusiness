package dao


import scala.concurrent.Future

import javax.inject.Inject
import models.Cart
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile



class CartDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  private val Carts = TableQuery[CartsTable]

  def all(): Future[Seq[Cart]] = db.run(Carts.result)

  def insert(cart: Cart): Future[Unit] = db.run(Carts += cart).map { _ => () }

  def delete(name: String): Future[Unit] = db.run(Carts.filter(_.item === item).delete).map(_ => ())



  private class CartsTable(tag: Tag) extends Table[Cart](tag, "CART") {

    def cartID=column[Int]("CART ID", O.AutoInc, O.AutoInc)
    def name=column[String]("NAME", O.PrimaryKey)
    def quantity=column[Int]("QUANTITY")
    
    def * =(cartID, name, quantity)<>(Cart.tupled, Cart.unapply _)
  }




}

