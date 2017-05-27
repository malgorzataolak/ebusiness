package models

/**
  * Created by gosia on 11.04.17.
  */
case class Product(name: String, description: String, price: Int, category: Category)
case class Cart(item: CartItem)
case class CartItem(cartItemID:Int, name: Product, quantity:Int, cartID:Cart )
case class Category(categoryName: String)
case class Order(idOrder:Int, firstName:String, lastName:String, email:String, streetName:String, houseNumber:Int, city:String,zipCode:String, country:String, phoneNumber:String)
case class OrderItem(cartID:Cart, itemName:Strinf, orderID:Int, quantity:Int, unitPrice:Int)

