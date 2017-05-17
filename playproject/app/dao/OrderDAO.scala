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

  private val Orders = TableQuery[OrdersTable]

  def all(): Future[Seq[Order]] = db.run(Orders.result)

  def insert(order: Order): Future[Unit] = db.run(Orders += order).map { _ => () }

  def delete(id:Int):Future[Unit]=db.run(Orders.filter(_.id===id).delete).map(_=>())


  private class OrdersTable(tag: Tag) extends Table[Order](tag, "ORDER") {

    def idOrder=column[Int]("ORDER ID" O.PrimaryKey)
    def firstName=column[String]("FIRST NAME")
    def lastName=column[String]("LAST NAME")
    def email=column[String]("EMAIL")
    def streetName = column[String]("STREET")
    def houseNumber=column[Int]("HOUSE NUMBER")
    def city = column[String]("CITY")
    def zipCode = column[String]("ZIP CODE")
    def country = column[String]("COUNTRY")
    def phoneNumber = column[String]("PHONE NUMBER")
    def *=(idOrder, firstame, lastName, email, streetName, houseNumber, city, zipCode, country, phoneNumber)<> Order.tupled, Order.unapply _)
  }

}
