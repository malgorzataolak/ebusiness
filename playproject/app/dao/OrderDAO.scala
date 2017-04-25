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

  private class OrdersTable(tag: Tag) extends Table[Order](tag, "ORDER") {

    /*stub for table configuration */
  }

}
