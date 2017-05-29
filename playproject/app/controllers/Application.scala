package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  
  def order=Action{
    Ok(views.html.order())
    }

   def cart=Action{
    Ok(views.html.cart())
    }

   def updateCart=Action{
    Ok(views.html.cart())
    }

   def addItemToCart(name:String)=Action{
    Ok(views.html.cart())
    }

    def removeItemFromCart(name:String)=Action{
    Ok(views.html.cart())
    }

}
