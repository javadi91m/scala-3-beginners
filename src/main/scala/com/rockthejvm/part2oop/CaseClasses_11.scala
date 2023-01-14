package com.rockthejvm.part2oop

object CaseClasses_11 {

  // case classes can be used as lightweight data structures => for holding some small data that are kept in a collection, ...
  case class Person(name: String, age: Int) {
    // do some other stuff
  }

  // 1 - class args are now promoted to fields
  val daniel = new Person("Daniel", 99)
  val danielsAge = daniel.age

  // 2 - toString, equals and hashCode
  // note: "equals" and "==" can check references or implementation of "equals", but "eq" checks the references only
  val danielToString = daniel.toString // Person("Daniel", 99)
  val danielDuped = new Person("Daniel", 99)
  val isSameDaniel = daniel == danielDuped // true
  val isSameDaniel2 = daniel equals  danielDuped // true
  val isSameDaniel3 = daniel eq danielDuped // false

  // 3 - utility methods
  val danielYounger = daniel.copy(age = 78) // new Person("Daniel", 78)

  // 4 - CCs have companion objects
  val thePersonSingleton = Person
  val daniel_v2 = Person("Daniel", 99) // "constructor"

  // 5 - CCs are serializable
  // use-case: Akka

  // 6 - CCs have extractor patterns for PATTERN MATCHING

  // can't create CCs with no arg lists
  /*
    case class CCWithNoArgs {
      // some code
    }

    val ccna = new CCWithNoArgs
    val ccna_v2 = new CCWithNoArgs // all instances would be equal!
  */

  // we cannot create a "case class" with no arg list, instead, we need to define a "case object"
  // we can also create a case class with no arg lists, but with parentheses: case class CCWithArgListNoArgs()
  case object UnitedKingdom {
    // fields and methods
    def name: String = "The UK of GB and NI"
  }

  case class CCWithArgListNoArgs[A]() // legal, mainly used in the context of generics

  /**
   * Exercise: use case classes for LList.
   */

  def main(args: Array[String]): Unit = {
    println(daniel)
    println(isSameDaniel)
  }
}
