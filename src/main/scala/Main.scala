import akka.actor.Status.Success

import scala.util.Try
import akka.actor.{Actor, ActorSystem, Props}
import akka.dispatch.sysmsg.Failed
import com.sun.net.httpserver.Authenticator.Failure

import scala.annotation.tailrec
//import akka.dispatch.sysmsg.Failed
//import akka.pattern.StatusReply.Success
import akka.actor.Status.Success
import akka.pattern.{ask, pipe}
import akka.util.Timeout
import com.sun.net.httpserver.Authenticator.Failure

import scala.concurrent.{Future}
import scala.concurrent.ExecutionContext.Implicits.global

object Main {
  def main(args: Array[String]): Unit = {

    // 1. FP. SCALA vs JAVA. EVERYTHING HAS A VALUE. STRONGLY TYPED (Ctrl B)
    // referential transparency

    // everything has a value --------------

    val ifStat: Boolean = if (1 > 2) true else false
    val forStat: List[Int] = (for (i <- 1 to 5) yield i).toList
    val printStat: Unit = println("hallo scala")
    val brackStat: Unit = ()

    // type hierarchy --------------

    // "Any" bad practice
    // Any -> AnyRef -> Objects...
    //     -> AnyVal -> Boolean, Int, Unit

    def notImplFunc(i: Int): Int = ???

    // evaluation --------------
    val a: Int = 1
    lazy val b: Int = 1

    // by value
    def myFunc(i: Int): Int = 1
    // by name (every time called)
    def myFunc2(i: => Int): Int = 1

    // all together --------------

    def ifStatFunc(a: Int, b: Int): Boolean = if (a > b) true else false
    def forStatFunc(london: (Int, Int) => Boolean, a:Int, b: Int ): List[Int] =
        if (london(a, b)) (for (i <- 1 to 5) yield i).toList else List()
    def myPrint(l: List[Int]): Unit = println(l)

    myPrint(forStatFunc(ifStatFunc, 3, 2))

    // val var (var is bad :))
    // recursion to help --------------

    // example 1
    class Myclass {
      var i:Int = 0
      def setA(a: Int): Unit = {i = a}
    }

    class MyBetterClass( i: Int) {
      def setI(a: Int) = new MyBetterClass(a)
    }
    val obj = new MyBetterClass(1)

    // example 2
    def sum(el: Int): Int = {
      var r: Int = 0
      for (i <- 0 to el) {
        r = r + el - i
      }
      r
    }
    def beterSum(el: Int): Int = {
      @tailrec
       def helper(hel: Int, acc: Int): Int = {
         if (hel <= 0) acc
         else helper(hel - 1, acc + hel )
      }
    helper(el, 0)
    }

    println(sum(3))
    println(beterSum(3))

    // 2. CONSTRUCTS. CLASSES, TRAITS, OBJECTS, COMPANION OBJECTS

    // classes --------------
    class ParentClas(i: Int) {
      def priintf() = println("")
    }
    // access constructor of parent class
    class NewClass(val i: Int) extends  ParentClas(i) {
      super.priintf()
      /// constructor
    }

    val newObj: NewClass = new NewClass(3)
    (new NewClass(12))

    // trait --------------

    trait A {
      def funcInTrait(i: Int): Int
    }

    class B extends A {
      override def funcInTrait(i: Int): Int = ???
    }

    // start next with:

    // go trough Diamond problem
    // case classes --------------
    // objects (companion objects) --------------

    // 3. EXCEPTION HANDLING. OPTIONS. EXCEPTIONS
    // options, try / catch, Try, fold

    // 4. HIGHER ORDER FUNCTIONS, FOR COMPREHENSIONS
    // anonymous function,  map,  flatmap,  for comprehension,  filter,  find

    // 5. CONCURRENCY - AKKA, FUTURES, FIBERS
    // akka
    // need library
    // In akka separate actors from implementation
    // dispatcher match messages to threads
    // dead letters can not be reposted
    // hard to trace, spaghetti pattern
    // simple example
    // async db call
    // failed state recovery
    // ? example

    // futures
    // fibers ---------------
    // many computations on single thread, (like Akka) result of Cats Effect and ZIO

    // 6. CURRYING IN FLUI

    // 7. IMPLICITS

    // 8. TYPE CLASSES

    // 9. WHY CATS ARE SIMPLE

    // 10. WHY ZIO, CATS EFFECT
    // fibers, ref. tr

    // 11. TAGLES FINAL
    // all code is abstract

  }
}
