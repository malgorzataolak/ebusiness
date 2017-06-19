package daos

import javax.inject.Inject

import models.{Carts, CartsREST}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}


class CartsDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  val Carts = TableQuery[CartsTable]

  def delete(id: Int): Future[Unit] ={ db.run(Carts.filter(_.id === id).delete).map { _ => () }}
  def clearUserCart(userID: String): Future[Unit] = db.run(Carts.filter(_.userID === userID).delete).map { _ => () }

  def getByUser(userID: String): Future[List[CartsREST]] = {
    val futureCarts = db.run(Carts.filter(_.userID === userID).result)
    futureCarts.map(
      _.map {
        a => CartsREST(id = a.id, productID = a.productID, userID = a.userID, productName = a.productName, productPrice = a. productPrice)
      }.toList)
  }

  def insert(cart: Carts): Future[Unit] = db.run(Carts += cart).map { _ => () }

  class CartsTable(tag: Tag) extends Table[Carts](tag, "Carts") {
    def id = column[Int]("id", O.AutoInc, O.AutoInc)
    def productID = column[Int]("productID")
    def userID = column[String]("userID")
    def productName = column[String]("productName")
    def productPrice = column[Float]("productPrice")
    
    def * = (id, productID, userID, productName, productPrice) <> (models.Carts.tupled, models.Carts.unapply)
  }

}
