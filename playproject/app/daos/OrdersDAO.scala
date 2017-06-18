package daos

import javax.inject.Inject

import models.{Orders, OrdersREST}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}


class OrdersDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  val Orders = TableQuery[OrdersTable]

  def all(implicit ec: ExecutionContext): Future[List[OrdersREST]] = {
    val query =  Orders
    val results = query.result
    val futureOrders = db.run(results)
    futureOrders.map(
      _.map {
        a => OrdersREST(id = a.id, productsList = a.productsList, userID = a.userID, totalPrice = a.totalPrice,
          paymentType = a.paymentType, shipmentType = a.shipmentType, address = a.address, date = a.date)
      }.toList)
  }

  def insert(order: Orders): Future[Unit] = db.run(Orders += order).map { _ => () }

  class OrdersTable(tag: Tag) extends Table[Orders](tag, "Orders") {
    def id = column[Int]("id", O.AutoInc, O.AutoInc)
    def productsList = column[String]("productsList")
    def userID = column[String]("userID")
    def totalPrice = column[Float]("totalPrice")
    def paymentType = column[String]("paymentType")
    def shipmentType = column[String]("shipmentType")
    def address = column[String]("address")
    def date = column[String]("date")
    
    def * = (id, productsList, userID, totalPrice, paymentType, shipmentType, address, date) <> (models.Orders.tupled, models.Orders.unapply)
  }

}
