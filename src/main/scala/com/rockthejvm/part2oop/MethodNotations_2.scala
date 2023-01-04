package com.rockthejvm.part2oop

object MethodNotations_2 {

  class Person(val name: String, val age: Int, favoriteMovie: String) {
    
    // in scala 2.x.x and 3.0.0, infix is optional and all methods with only one argument are eligible for infix notatopn,
    // but this can change in newer versions, so better explicitly mention this
    infix def likes(movie: String): Boolean =
      movie == favoriteMovie

    infix def +(person: Person): String =
      s"${this.name} is hanging out with ${person.name}"

    infix def +(nickname: String): Person =
      new Person(s"$name ($nickname)", age, favoriteMovie)

    infix def !!(progLanguage: String): String =
      s"$name wonders how can $progLanguage be so cool!"

    // prefix position
    // unary ops: -, +, ~, ! => no args, no parenthesis
    // make sure to start the name with "unary_" and then write your desired name.
    // after completing the name, make sure to include a "white space" and then include ":" (for mentioning the return type)
    // otherwise compiler thinks ":" is part of the method name
    def unary_- : String =
      s"$name's alter ego"

    def unary_+ : Person =
      new Person(name, age + 1, favoriteMovie)

    // postfix notation => they don't take any arguments AND we need "import scala.language.postfixOps"
    def isAlive: Boolean = true

    // apply method is special, we can eliminate "apply" while calling the method
    // e.g. person.apply() => person() AND person.apply(10) => person(10)
    def apply(): String =
      s"Hi, my name is $name and I really enjoy $favoriteMovie"

    def apply(n: Int): String =
      s"$name watched $favoriteMovie $n times"
  }

  val mary = new Person("Mary", 34, "Inception")
  val john = new Person("John", 36, "Fight Club")

  val negativeOne = -1

  /**
   *  Exercises
   *  - a + operator on the Person class that returns a person with a nickname
   *    mary + "the rockstar" => new Person("Mary the rockstar", _, _)
   *  - a UNARY + operator that increases the person's age
   *    +mary => new Person(_, _, age + 1)
   *  - an apply method with an int arg
   *    mary.apply(2) => "Mary watched Inception 2 times"
   */

  def main(args: Array[String]): Unit = {
    println(mary.likes("Fight Club"))
    // infix notation - for methods with ONE argument
    println(mary likes "Fight Club") // identical

    // "operator" = plain method
    println(mary + john)
    println(mary.+(john)) // identical
    println(2 + 3)
    println(2.+(3)) // same
    println(mary !! "Scala")

    // prefix position
    println(-mary)

    // postfix notation
    println(mary.isAlive)

    import scala.language.postfixOps
    println(mary isAlive) // discouraged

    // apply is special
    println(mary.apply())
    println(mary()) // same

    // exercises
    val maryWithNickname = mary + "the rockstar"
    println(maryWithNickname.name)

    val maryOlder = +mary
    println(maryOlder.age) // 35

    println(mary(10))
  }
}
