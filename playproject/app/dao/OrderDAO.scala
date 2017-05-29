package dao

import scala.concurrent.Future

import javax.inject.Inject
import models.Order
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile


class OrderDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  private val Orders =TableQuery[OrdersTable]
  private val OrderItems=TableQuery[OrderItems]
  private val OrderInfos=TableQuery[OrderInfos]

  def all(): Future[Seq[Order]] = db.run(Orders.result)

  def insert(order: Order): Future[Unit] = db.run(Orders += order).map { _ => () }

  def delete(id:Int):Future[Unit]=db.run(Orders.filter(_.id===id).delete).map(_=>())

   

  private class OrdersTable(tag: Tag) extends Table[Order](tag, "ORDER") {

    def idOrder=column[Int]("ORDER ID" O.PrimaryKey, O.AutoInc)
    def customerID=column[String]("customerID")
    def *=(idOrder.?, customerID)<> Order.tupled, Order.unapply _)
  }

  private class OrderItem(tag: Tag) extends Table[OrderItem](tag, "ORDER ITEM"){
    def id=column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def orderID=column[Int]("ORDER ID")
    def productID=column[Int]("PRODUCT ID")
    def quantity=column[Int]("QUANTITY")
    def *=(id.?, orderID, productID, quantity)<> Order.tupled, Order.unapply _)
    }

  private class OrderInfos(tag: Tag) extends Table[OrderInfos](tag, "ORDER INFO"){
    def id=column[Int]("ID", O.PrimaryKey,O.AutoInc)
    def orderID=column[Int]("ORDER ID")
    def firstName=column[String]("FIRST NAME")
    def lastName=column[String]("LAST NAME")
    def email=column[String]("EMAIL")
    def streetName = column[String]("STREET")
    def houseNumber=column[Int]("HOUSE NUMBER")
    def city = column[String]("CITY")
    def zipCode = column[String]("ZIP CODE")
    def country = column[String]("COUNTRY")
    def phoneNumber = column[String]("PHONE NUMBER")
    def*=(id, orderID, firstName, lastName, email, streetName, houseNumber, city, zipCode, country, phoneNumber)<> Order.tupled, Order.unapply _)
    }

}
