package pl.softech.learning.ch3

import pl.softech.learning.ch3.List.Implicits._

object Ex5 {

  def setHead[A](l: List[A], a: A): List[A] = l match {
    case Cons(h, t) => Cons(a, t)
    case Nil => Cons(a, Nil)
  }

  trait Implicits {

    implicit class Ex5ListOpts[A](l: List[A]) {
      def setHead(a: A): List[A] = Ex5.setHead(l, a)
    }

  }

  def main(args: Array[String]): Unit = {
    println(List(1, 2, 3, 4, 5).setHead(9))
  }

}
