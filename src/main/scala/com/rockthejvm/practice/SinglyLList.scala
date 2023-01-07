package com.rockthejvm.practice

import scala.annotation.tailrec

abstract class SinglyLList[A] {

  def head: A
  def tail: SinglyLList[A]
  def isEmpty: Boolean
  def add(element: A): SinglyLList[A] = new SinglyLinkedList[A](element, this)

}

class EmptyList[A]() extends SinglyLList[A] {
  override def head: A = throw new NoSuchElementException("List is empty")

  override def tail: SinglyLList[A] = throw new NoSuchElementException("List is empty")

  override def isEmpty: Boolean = true

  override def toString: String = "[]"


}

// we're overriding head/tail methods (accessor methods) as class fields
class SinglyLinkedList[A](override val head: A, override val tail: SinglyLList[A]) extends SinglyLList[A] {
  override def isEmpty: Boolean = false

  override def toString: String = {
    @tailrec
    def concatenate(accumulator: String, list: SinglyLList[A]): String = {
      if (list.isEmpty) accumulator
      else concatenate(s"$accumulator, ${list.head}", list.tail)
    }
    s"[${concatenate(s"$head", this.tail)}]"
  }
}

object SinglyLList extends App {

  // compiler can infer generic types without explicitly typing them
  val list: SinglyLList[Int] = new SinglyLinkedList(10, new SinglyLinkedList(7, new SinglyLinkedList(14, new EmptyList)))
  val list2: SinglyLList[Int] = new EmptyList().add(1).add(4).add(3).add(67).add(-1)
  println(list)
  println(list2)

}
