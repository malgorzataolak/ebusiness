package models

import play.api.libs.json.Format

case class Products(id: Int, categoryID: Int, name: String, description: String, price: Float)
case class Carts(id: Int, productID: Int, userID: String, productName: String, productPrice: Float)
case class Categories(id: Int, name: String)
case class Orders(id: Int, productsList: String, userID: String, totalPrice: Float, 
	paymentType: String, shipmentType: String, address: String, date: String)
