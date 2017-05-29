package controllers

import play.api._
import play.api.mvc._

object ProductController extends Controller{

    def addNewProduct=Action{
    Ok(views.html.addNewProduct())
  }


  def productsDetial(name:String)=Action{
    Ok(views.html.productsDetail())
    }

  def productsByCategory(categoryName:String)=Action{
    Ok(views.html.productsByCategory())
    }

  def editProduct=Action{
    Ok(views.html.productsDetail())
    }


}
