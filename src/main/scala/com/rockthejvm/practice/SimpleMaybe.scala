package com.rockthejvm.practice

trait SimpleMaybe[A] {

  def value: A

  def filter(operator: A => Boolean): SimpleMaybe[A]

  def map[B](operator: A => B): SimpleMaybe[B]

  def flatMap[B](operator: A => SimpleMaybe[B]): SimpleMaybe[B]

}

case class EmptyMaybe[A]() extends SimpleMaybe[A] {

  override def value: A = throw new NoSuchElementException("Maybe is Empty")

  override def filter(operator: A => Boolean): SimpleMaybe[A] = this

  override def map[B](operator: A => B): SimpleMaybe[B] = EmptyMaybe[B]()

  override def flatMap[B](operator: A => SimpleMaybe[B]): SimpleMaybe[B] = EmptyMaybe[B]()

}

case class ValueMaybe[A](override val value: A) extends SimpleMaybe[A] {

  override def filter(operator: A => Boolean): SimpleMaybe[A] =
    if (operator(value)) this
    else EmptyMaybe()

  override def map[B](operator: A => B): SimpleMaybe[B] = ValueMaybe(operator(value))

  override def flatMap[B](operator: A => SimpleMaybe[B]): SimpleMaybe[B] = operator(value)

}

object test extends App {

  val value = ValueMaybe(10)

  println(value.value)

  val res = value.filter(_ % 2 == 0)
    .map(_ * 3)
    .flatMap(x => {
      if (System.currentTimeMillis() % 2 == 0) ValueMaybe(x)
      else EmptyMaybe()
    })

  println(res)

}