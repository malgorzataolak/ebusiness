package dao

import scala.concurrent.Future

import javax.inject.Inject
import models.OrderItem
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile


class OrderItemDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  private val OrderItems = TableQuery[OrderItemsTable]

  def all(): Future[Seq[OrderItem]] = db.run(OrderItems.result)

  def insert(order: OrderItem): Future[Unit] = db.run(OrderItems += order).map { _ => () }

  def delete(id:Int):Future[Unit]=db.run(OrderItems.filter(_.id===id).delete).map(_=>())


  private class OrderItemTable(tag: Tag) extends Table[OrderItem](tag, "ORDER ITEM") {

    def itemName=column[String]("ITEM NAME", O.PrimaryKey)
    def orderID=column[Int]("ORDER ID")
    def quantity=column[Int]("QUANTITY")
    def unitPrice=column[Int]("UNIT PRICE")
    def *=(idOrder, firstame, lastName, email, streetName, houseNumber, city, zipCode, country, phoneNumber)<> Order.tupled, Order.unapply _)
  }

}
