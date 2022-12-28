package com.rockthejvm.part2oop

object OOBasics_1 {

  // if we want to make a class with class fields immutable, and if we have this requirement to change some of the instance attributes,
  // we need to return a new instance on every mutation

  // convention: if you have a no arg method, you can write it with/without parenthesis.
  // if that method just returns a value (like getter methods) without doing any action, better to write them without parenthesis
  // if that method does some action (e.g. print() ), better to write the parenthesis

  // classes
  // if you don't put "val"/"var" before a constructor argument, Scala keeps it as a private variable which is accessible only through the class
  // it means class parameters are not the same as class fields!
  class Person(val name: String, age: Int) { // constructor signature
    // fields => all instances of a class will have this field (public)
    val allCaps = name.toUpperCase()

    // methods
    def greet(name: String): String =
      s"${this.name} says: Hi, $name"

    // signature differs
    // OVERLOADING
    def greet(): String =
      s"Hi, everyone, my name is $name and I'm $age years old"

    // aux constructor
    // auxiliary constructors are not that relevant in Scala, because we have to call another constructor in them. we can achieve the same by providing default values ib the main constructor
    def this(name: String) =
      this(name, 0)

    def this() =
      this("Jane Doe")
  }

  val aPerson: Person = new Person("John", 26)
  val john = aPerson.name // class parameter != field
  val johnSayHiToDaniel = aPerson.greet("Daniel")
  val johnSaysHi = aPerson.greet()
  val genericPerson = new Person()

  def main(args: Array[String]): Unit = {
    val charlesDickens = new Writer("Charles", "Dickens", 1812)
    val charlesDickensImpostor = new Writer("Charles", "Dickens", 2021)

    val novel = new Novel("Great Expectations", 1861, charlesDickens)
    val newEdition = novel.copy(1871)

    println(charlesDickens.fullName)
    println(novel.authorAge)
    println(novel.isWrittenBy(charlesDickensImpostor)) // false
    println(novel.isWrittenBy(charlesDickens)) // true
    println(newEdition.authorAge)

    val counter = new Counter()
    counter.print() // 0
    counter.increment().print() // 1
    counter.increment() // always returns new instances
    counter.print() // 0

    counter.increment(10).print() // 10
    counter.increment(20000).print() //
  }
}

/**
  Exercise: imagine we're creating a backend for a book publishing house.
  Create a Novel and a Writer class.

  Writer: first name, surname, year
    - method fullname

  Novel: name, year of release, author
    - authorAge
    - isWrittenBy(author)
    - copy (new year of release) = new instance of Novel
 */

class Writer(firstName: String, lastName: String, val yearOfBirth: Int) {
  def fullName: String = s"$firstName $lastName"
}

class Novel(title: String, yearOfRelease: Int, val author: Writer) {
  def authorAge: Int = yearOfRelease - author.yearOfBirth
  def isWrittenBy(author: Writer): Boolean = this.author == author
  def copy(newYear: Int): Novel = new Novel(title, newYear, author)
}

/**
 * Exercise #2: an immutable counter class
 * - constructed with an initial count
 * - increment/decrement => NEW instance of counter
 * - increment(n)/decrement(n) => NEW instance of counter
 * - print()
 *
 * Benefits:
 * + well in distributed environments
 * + easier to read and understand code
 */

class Counter(count: Int = 0) {
  def increment(): Counter =
    new Counter(count + 1)

  def decrement(): Counter =
    if (count == 0) this
    else new Counter(count - 1)

  def increment(n: Int): Counter =
    if (n <= 0) this
    else increment().increment(n - 1) // vulnerable to StackOverflow

  def decrement(n: Int): Counter =
    if (n <= 0) this
    else decrement().decrement(n - 1)

  def print(): Unit =
    println(s"Current count: $count")
}