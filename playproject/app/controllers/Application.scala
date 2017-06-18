package controllers

import javax.inject.Inject

import daos.ProductsDAO
import models.ProductsREST
import models.Products

import daos.CategoriesDAO
import models.CategoriesREST
import models.Categories

import daos.CartsDAO
import models.CartsREST
import models.Carts

import daos.OrdersDAO
import models.OrdersREST
import models.Orders

import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class Application @Inject() (productsDAO: ProductsDAO, categoriesDAO: CategoriesDAO,
	cartsDAO: CartsDAO, ordersDAO: OrdersDAO) extends Controller {

  def index = Action.async { implicit  request =>
    productsDAO.all map {
      products => Ok(Json.toJson(products))
    }
  }

  def newProduct = Action { implicit request =>
    var json:ProductsREST = request.body.asJson.get.as[ProductsREST]
    var product = Products(id = 0, categoryID = json.categoryID, name = json.name,
     description = json.description, price = json.price)
    productsDAO.insert(product)
    Ok(request.body.asJson.get)
  }

  def newCategory = Action { implicit request =>
    var json:CategoriesREST = request.body.asJson.get.as[CategoriesREST]
    var category = Categories(id = 0, name = json.name)
    categoriesDAO.insert(category)
    Ok(request.body.asJson.get)
  }

  def getOrders = Action.async { implicit  request =>
    ordersDAO.all map {
      orders => Ok(Json.toJson(orders))
    }
  }

  def getCategories = Action.async { implicit  request =>
    categoriesDAO.all map {
      categories => Ok(Json.toJson(categories))
    }
  }

  def getProductsByCategory(categoryID: Int) = Action.async { implicit  request =>
    productsDAO.getByCategory(categoryID) map {
      products => Ok(Json.toJson(products))
    }
  }

  def getUserCart(userID: String) = Action.async { implicit  request =>
    cartsDAO.getByUser(userID) map {
      cartProducts => Ok(Json.toJson(cartProducts))
    }
  }

  def addCartProduct = Action { implicit request =>
  	var json:CartsREST = request.body.asJson.get.as[CartsREST]
  	var cartProduct = Carts(id = 0, productID = json.productID, userID = json.userID,
  		productName = json.productName, productPrice = json.productPrice)
  	cartsDAO.insert(cartProduct)
  	Ok(request.body.asJson.get)
  }

  def removeCartProduct(cartID: Int) = Action { implicit request =>
  	cartsDAO.delete(cartID)
  	Ok("200")
  }

  def createOrder = Action { implicit request =>
  	var json:OrdersREST = request.body.asJson.get.as[OrdersREST]
  	var newOrder = Orders(id = 0, productsList = json.productsList, userID = json.userID,
  		totalPrice = json.totalPrice, paymentType = json.paymentType,
  		shipmentType = json.shipmentType, address = json.address, date = json.date)

  	ordersDAO.insert(newOrder)
  	Ok(request.body.asJson.get)

  }
}
