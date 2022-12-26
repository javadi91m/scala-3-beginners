package com.rockthejvm.part1basics

object StringOps_7 {

  val aString: String = "Hello, I am learning Scala"

  // string functions
  val secondChar = aString.charAt(1)
  val firstWord = aString.substring(0, 5) // "Hello"
  val words = aString.split(" ") // Array("Hello,", "I", "am", "learning", "Scala")
  val startsWithHello = aString.startsWith("Hello") // true
  val allDashes = aString.replace(' ', '-')
  val allUppercase = aString.toUpperCase() // also toLowerCase()
  val nChars = aString.length

  // other functions
  val reversed = aString.reverse
  val aBunchOfChars = aString.take(10)

  // parse to numeric
  val numberAsString = "2"
  val number = numberAsString.toInt

  // s-interpolation (probably standard-interpolation)
  val name = "Alice"
  val age = 12
  val greeting = "Hello, I'm " + name + " and I am " + age + "years old."
  val greeting_v2 = s"Hello, I'm $name and I'm $age years old."
  val greeting_v3 = s"Hello, I'm $name and I will be turning ${age + 1} years old."

  // f-interpolation (format-interpolation)
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.5f burgers per minute."

  // raw-interpolation
  val escapes = raw"This is a \n newline"

  def main(args: Array[String]): Unit = {
    println(escapes)

    // regex
    // Using regular expressions:
    val theUltimateAnswer: String = "To life, the universe, and everything is 42."
    val myRegex = """.* (\d+).*""".r
    val myRegex(answerString) = theUltimateAnswer //> answerString  : String = 42 => note: answerString is a new val which is the actual result!!
    val answer = answerString.toInt //> answer  : Int = 42
    println(answer)

  }
}
