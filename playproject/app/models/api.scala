package models

import play.api.libs.json.Json


case class ProductsREST(id: Int, categoryID: Int, name: String, description: String, price: Float)
case class CartsREST(id: Int, productID: Int, userID: String, productName: String, productPrice: Float)
case class CategoriesREST(id: Int, name: String)
case class OrdersREST(id: Int, productsList: String, userID: String, totalPrice: Float, 
	paymentType: String, shipmentType: String, address: String, date: String)

object ProductsREST {
  implicit val productsFormat = Json.format[ProductsREST]
}

object CartsREST {
  implicit val productsFormat = Json.format[CartsREST]
}

object CategoriesREST {
  implicit val productsFormat = Json.format[CategoriesREST]
}

object OrdersREST {
  implicit val productsFormat = Json.format[OrdersREST]
}
