/**
  * Created by gosia on 26.03.17.
  */

import java.util.Scanner

import util.control.Breaks.{break, _}


class TicTacToe {

  val board = Array.ofDim[String](3, 3)
  var currentPlayer="CROSS"
  var row=0
  var col=0
  var isExit=false
  var countMoves=0

  def initializeBoard():Unit =
  {
    val range = 0 until 3
    for (i: Int <- range; j: Int <- range) {
      board(i)(j) = "N"
    }
  }

  def printBoard(): Unit= {
    print(" " + board.map(_.mkString(" | ")).mkString("\n------------\n "))
  }

  def game():Unit ={
    println("Witaj w grze kolko i krzyzyk!\n")
    initializeBoard()
    while(!isExit) {
      println("\n \n  Teraz gra "+currentPlayer+"\n")
      var move = false
      var mark = ""
      printBoard()
      if (currentPlayer == "CROSS") {
        println("\n\nWstaw X")
        mark = "X"
      } else {
        println("\n\nWstaw O")
        mark = "O"
      }
      while (!move) {
        makeMove()
        if (board(row)(col) == "N") {
          board(row)(col) = mark
          move = true
          countMoves+=1
          printBoard()
        } else {
          println("\nNiestety, podane pole jest zajęte! Wprowadz dane jeszcze raz\n")
        }
      }
      if (checkIfWinner(mark, row, col)) {
        println("\nGracz " + currentPlayer + " wygral\n")
        isExit = true
        println("Dziekujemy za gre!")
      }else if(countMoves==9){
            isExit=true
            println("\n\nTo byl ostatni ruch. Niestety nikt nie wygral!")
      } else {
        changePlayer()
      }
    }

    println("Koniec gry.")

  }

  def changePlayer()={
    if(currentPlayer=="CROSS"){
      currentPlayer="CIRCLE"
    }else {
      currentPlayer="CROSS"
    }

  }

  //przechowuje numer rzedu i kolumny
  def makeMove():Unit ={

    var checkCol=true
    var checkRow=true
    val scan = new Scanner(System.in)
    breakable {
      while (true) {
        while(checkRow) {
          println("Podaj numer rzędu od 1 do 3:")
          row = scan.nextInt() - 1
          if (row > 2 || row < 0) {
            println("To nie jest cyfra z przedziału 1 do 3! Wpisz jeszcze raz.")
          } else checkRow=false
        }
        while(checkCol){
        println("Podaj numer kolumny od 1 do 3:")
        col = scan.nextInt()-1

          if (col > 2 || col<0) { println("To nie jest cyfra z przedziału 1 do 3! Wpisz jeszcze raz.")
          }else checkCol=false
        }
        break()
      }
    }
  }

  def checkIfWinner(currentPlayer:String, currentRow: Int, currentCol: Int):Boolean ={
        if(board(currentRow)(0)==currentPlayer && board(currentRow)(1)==currentPlayer && board(currentRow)(2)==currentPlayer ||
          board(0)(currentCol)==currentPlayer && board(1)(currentCol)==currentPlayer && board(2)(currentCol)==currentPlayer ||
          currentRow==currentCol && board(0)(0)==currentPlayer && board(1)(1)==currentPlayer && board(2)(2)==currentPlayer ||
        currentRow + currentCol ==2 && board(0)(2)==currentPlayer && board(1)(1)==currentPlayer && board(2)(0)==currentPlayer){
          return true
        }else return false
  }



}




val playTTT = new TicTacToe

playTTT.game()


