/**
  * Created by gosia on 09.04.17.
  */

import scala.io.Source
import java.io.FileNotFoundException


trait Reader{
  def read(): String
  def upperCase(text: String):String={
    val upperText = text.map(c => c.toUpper)
    return upperText
  }
  def whiteSpaceClean(text: String):String={
    val noWhiteSpaceText = text.filterNot((x: Char) => x.isWhitespace)
    return noWhiteSpaceText
  }
}


class InputReader extends Reader{
  override def read():String = {
    var sentence=""
    var ok=false
    while(ok==false){
      println("Wpisz zdanie, ktore chcesz zmodyfikowac")
      sentence = scala.io.StdIn.readLine()
      if (sentence == "") {
        println("Brak zdania - wpisz jeszcze raz")

      }else ok=true
    }
    return sentence
  }
}

class FileReader extends Reader{
  override def read():String = {
    var path="/home/gosia/Dokumenty/"
    println("Podaj nazwe pliku")
    var textFromFile=""
    var fileName=scala.io.StdIn.readLine()
    try {
      for (line <- Source.fromFile(path + fileName).getLines) {
        textFromFile += line + "\n"
      }
    }catch {
      case ex: FileNotFoundException => {
        println("Nie ma takiego pliku")
      }
    }
    return textFromFile
  }
}

trait ReaderDecorator extends Reader {
  def read():String

}

class CapitalizeInputReader(mojObiekt:Reader) extends ReaderDecorator{
  def read():String ={
    return upperCase(this.mojObiekt.read())
  }


}

class WhiteSpaceInputReader(mojObiekt: Reader) extends ReaderDecorator{
  def read():String={
    return whiteSpaceClean(this.mojObiekt.read())
  }
}

class CapitalizeFileReader(mojObiekt: Reader) extends ReaderDecorator{
  def read():String={
    return upperCase(this.mojObiekt.read())
  }
}

class WhiteSpaceFileReader(mojObiekt: Reader) extends ReaderDecorator{
  def read():String={
    return whiteSpaceClean(this.mojObiekt.read())
  }
}


object Test {
  def main(args: Array[String]): Unit = {

    val nowyText=new InputReader
    val dekorujText=new WhiteSpaceInputReader(nowyText).read()
    println(dekorujText)
    val dekorujTextUpper=new CapitalizeFileReader(nowyText).read()
    println(dekorujTextUpper)

    val nowyText2=new FileReader
    val dekorujTextzPliku=new CapitalizeFileReader(nowyText2).read()
    println(dekorujTextzPliku)
    val dekorujTextzPliku2=new WhiteSpaceFileReader(nowyText2).read()
    println(dekorujTextzPliku2)

  }
}

