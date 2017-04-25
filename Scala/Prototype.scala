/**
  * Created by gosia on 22.04.17.
  */
import java.lang.Cloneable

abstract class CloneableProduct(name:String, quantity: Int, price: Int, available: Boolean) {

  def clone():CloneableProduct

}



class ProductPrototype(name:String, quantity: Int, price: Int, available: Boolean) extends CloneableProduct(name:String, quantity: Int, price: Int, available: Boolean){


  def getName() : String={
          return name
  }


  def getQuantity():Int={
    return quantity
  }

  def getPrice():Int={
    return price
  }

  def isAvailable():Boolean={
    return available
  }

  override def clone(): ProductPrototype={
    return new ProductPrototype()

  }

}



