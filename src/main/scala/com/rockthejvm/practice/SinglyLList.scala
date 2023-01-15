package com.rockthejvm.practice

import scala.annotation.tailrec

abstract class SinglyLList[A] {

  def head: A

  def tail: SinglyLList[A]

  def isEmpty: Boolean

  def add(element: A): SinglyLList[A] = SinglyLinkedList(element, this)

  def filter(predicate: A => Boolean): SinglyLList[A]

  def map[B](transformer: A => B): SinglyLList[B]

  def flatMap[B](transformer: A => SinglyLList[B]): SinglyLList[B]

  infix def ++(anotherList: SinglyLList[A]): SinglyLList[A]

  def find(predicate: A => Boolean): A

}

case class EmptyList[A]() extends SinglyLList[A] {
  override def head: A = throw new NoSuchElementException("List is empty")

  override def tail: SinglyLList[A] = throw new NoSuchElementException("List is empty")

  override def isEmpty: Boolean = true

  override def toString: String = "[]"

  override def filter(predicate: A => Boolean): SinglyLList[A] = EmptyList()

  override def map[B](transformer: A => B): SinglyLList[B] = EmptyList()

  override def flatMap[B](transformer: A => SinglyLList[B]): SinglyLList[B] = EmptyList()

  override infix def ++(anotherList: SinglyLList[A]): SinglyLList[A] = anotherList

  override def find(predicateL: A => Boolean): A = throw new NoSuchElementException("List is empty")

}

// we're overriding head/tail methods (accessor methods) as class fields
case class SinglyLinkedList[A](override val head: A, override val tail: SinglyLList[A]) extends SinglyLList[A] {
  override def isEmpty: Boolean = false

  override def toString: String = {
    @tailrec
    def concatenate(accumulator: String, list: SinglyLList[A]): String = {
      if (list.isEmpty) accumulator
      else concatenate(s"$accumulator, ${list.head}", list.tail)
    }

    s"[${concatenate(s"$head", this.tail)}]"
  }

  override def filter(predicate: A => Boolean): SinglyLList[A] =
    if (predicate(head)) SinglyLinkedList(head, tail.filter(predicate))
    else tail.filter(predicate)

  // implementation below is tailrec, but requires reversing at the end of the process
  //    def filter(list: SinglyLList[A], result: SinglyLList[A]): SinglyLList[A] = {
  //      if (list.isEmpty) result
  //      else if (predicate.test(list.head)) filter(list.tail, new SinglyLinkedList[A](list.head, result))
  //      else filter(list.tail, result)
  //    }
  //
  //    filter(this, new EmptyList[A])

  override def map[B](transformer: A => B): SinglyLList[B] =
    SinglyLinkedList(transformer(head), tail.map(transformer))

  override def flatMap[B](transformer: A => SinglyLList[B]): SinglyLList[B] =
    transformer(head) ++ tail.flatMap(transformer)

  override infix def ++(anotherList: SinglyLList[A]): SinglyLList[A] =
    if (anotherList.isEmpty) this
    else SinglyLinkedList(this.head, tail ++ anotherList)

  override def find(predicate: A => Boolean): A =
    if (predicate(head)) head
    else tail.find(predicate)

}

object SinglyLList extends App {

  // compiler can infer generic types without explicitly typing them
  val list: SinglyLList[Int] = SinglyLinkedList(10, SinglyLinkedList(7, SinglyLinkedList(14, EmptyList())))
  val list2: SinglyLList[Int] = EmptyList().add(1).add(4).add(3).add(67).add(-1).add(-2).add(6)
  println(list)

  println(list2 ++ list)
  println(list2.toString + " filter => " + list2.filter(x => x % 2 == 0))
  println(list2.toString + " map => " + list2.map(x => x * 2))
  println(list2.toString + " flatMap => " + list2.flatMap(x => SinglyLinkedList(x, SinglyLinkedList(x * 2, EmptyList()))))

  println(list2.find(x => x == -1))
}


/**
 * Exercise: LList extension
 *
 * 1.  Generic trait Predicate[T] with a little method test(T) => Boolean
 * 2.  Generic trait Transformer[A, B] with a method transform(A) => B
 * 3.  LList:
 * - map(transformer: Transformer[A, B]) => LList[B]
 * - filter(predicate: Predicate[A]) => LList[A]
 * - flatMap(transformer from A to LList[B]) => LList[B]
 *
 * class EvenPredicate extends Predicate[Int]
 * class StringToIntTransformer extends Transformer[String, Int]
 *
 * [1,2,3].map(n * 2) = [2,4,6]
 * [1,2,3,4].filter(n % 2 == 0) = [2,4]
 * [1,2,3].flatMap(n => [n, n+1]) => [1,2, 2,3, 3,4]
 */