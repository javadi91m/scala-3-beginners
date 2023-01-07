package com.rockthejvm.practice

import scala.annotation.tailrec

abstract class SinglyLList {

  def head: Int
  def tail: SinglyLList
  def isEmpty: Boolean
  def add(element: Int): SinglyLList = new SinglyLinkedList(element, this)

}

object EmptyList extends SinglyLList {
  override def head: Int = throw new NoSuchElementException("List is empty")

  override def tail: SinglyLList = throw new NoSuchElementException("List is empty")

  override def isEmpty: Boolean = true

  override def toString: String = "[]"


}

// we're overriding head/tail methods (accessor methods) as class fields
class SinglyLinkedList(override val head: Int, override val tail: SinglyLList) extends SinglyLList {
  override def isEmpty: Boolean = false

  override def toString: String = {
    @tailrec
    def concatenate(accumulator: String, list: SinglyLList): String = {
      if (list.isEmpty) accumulator
      else concatenate(s"$accumulator, ${list.head}", list.tail)
    }
    s"[${concatenate(s"$head", this.tail)}]"
  }
}

object SinglyLList extends App {

  val list = new SinglyLinkedList(10, new SinglyLinkedList(7, new SinglyLinkedList(14, EmptyList)))
  val list2 = EmptyList.add(1).add(4).add(3).add(67).add(-1)
  println(list)
  println(list2)

}
