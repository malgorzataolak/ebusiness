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

    def idOrder=column[Int]("ORDER ID")
    def productName=column[String]("NAME", O.PrimaryKey)
    def quantity=column[Int]("QUANTITY")
    def *=(idOrder, productName, quantity)<> Order.tupled, Order.unapply _)
  }

}
