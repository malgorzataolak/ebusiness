/**
  * Created by gosia on 22.04.17.
  */

abstract class Parser{
  def parse(text:String): Unit
}


class CVSParser extends Parser{

  override def parse(text:String):Unit={
    println(text+" ::CVSParser")
  }
}

class JsonParser extends Parser{

  override def parse(text:String):Unit={
    println(text+" ::JsonParser")
  }

}

class Application(fileToParse: String){
  val file = scala.io.Source.fromFile(fileToParse)
  val config = try file.mkString finally file.close()
  context().parse(config)


  def context(): Parser= {
    var fileType = fileToParse.split("\\.").last
    if (fileType == "json") {
      return new JsonParser()
    } else{
      return new CVSParser()
    }

  }
}


