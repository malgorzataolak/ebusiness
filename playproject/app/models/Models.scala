package models

/**
  * Created by gosia on 11.04.17.
  */
case class Product(name: String, description: String, price: Int, category: Category)
case class Cart(item: CartItem)
case class CartItem(cart:Cart, item: Product)
case class Category(categoryName: String)
case class Order(firstName:String, lastName: String, email: String, totalPrice: Int, cart: Cart )

