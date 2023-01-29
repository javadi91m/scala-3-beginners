package com.rockthejvm.part4power

object BracelessSyntax_3 {

  // Scala 3 has introduced some ways of writing things. we explore them in this lesson
  // in this way, instead of using braces ({}), we can use indentations

  // if - expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java-style
  val anIfExpression_v2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  // compact
  val anIfExpression_v3 =
    if (2 > 3) "bigger"
    else "smaller"

  // SCALA 3
  val anIfExpression_v4 =
    if 2 > 3 then // no parentheses, no braces => then is required in this way
      "bigger" // higher indentation than the if part
    else if 3 > 5 then
      "even bigger"
    else
      "smaller"

  // we can have more lines in each indentation
  val anIfExpression_v5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  // scala 3 one-liner => no parentheses in if
  val anIfExpression_v6 = if 2 > 3 then "bigger" else "smaller"

  // for comprehensions
  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  // SCALA 3
  val aForComprehension_v2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  // pattern matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"
  }

  // SCALA 3
  val aPatternMatch_v2 =
    meaningOfLife match
      case 1 => "the one"
      case 2 => "double or nothing"
      case _ => "something else"

  val aPatternMatch_v3 = meaningOfLife match
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"

  // methods without braces
  def computeMeaningOfLife(arg: Int): Int = // significant indentation starts here - think of it like a phantom code block
    val partialResult = 40





    partialResult + 2 // still part of the method implementation!

  // class definition with significant indentation (same for traits, objects, enums etc)
  class Animal: // compiler expects the body of Animal => ":" is required if you remove {}
    def eat(): Unit =
      println("I'm eating")
    end eat // "end eat" is optional (it's called "end token")

    def grow(): Unit =
      println("I'm growing")

    // 3000 more lines of code => if class is really large, better to use an "optional" end token to make it more readable
  end Animal // if, match, for, methods, classes, traits, enums, objects => here also "end token" is optional

  // it's also possible for anonymous classes:
  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("I'm special")

  // indentation = strictly larger indentation => it simply means new line has to have more indentation than previous line
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 3 spaces + 2 tabs > 3 spaces + 1 tab
  // 3 tabs + 2 spaces ??? 2 tabs + 3 spaces => it's hard to mix&match them => best practice: don't mix&match them!


  def main(args: Array[String]): Unit = {
    println(computeMeaningOfLife(78))
  }

}
