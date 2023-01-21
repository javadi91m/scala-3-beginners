package com.rockthejvm.part3fp

object MapFlatMapFilterFor_4 {

  // standard list
  val aList = List(1,2,3) // [1] -> [2] -> [3] -> Nil (Nil is empty list) // [1,2,3]
  val firstElement = aList.head
  val restOfElements = aList.tail

  // map
  val anIncrementedList = aList.map(_ + 1)

  // filter
  val onlyOddNumbers = aList.filter(_ % 2 != 0)

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  val aFlatMappedList = aList.flatMap(toPair) // [1,2,2,3,3,4]

  // All the possible combinations of all the elements of those lists, in the format "1a - black"
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white", "red")

  /*
    lambda = num => chars.map(char => s"$num$char")
    [1,2,3,4].flatMap(lambda) = ["1a", "1b", "1c", "1d", "2a", "2b", "2c", "2d", ...]
    lambda(1) = chars.map(char => s"1$char") = ["1a", "1b", "1c", "1d"]
    lambda(2) = .. = ["2a", "2b", "2c", "2d"]
    lambda(3) = ..
    lambda(4) = ..
   */
  // withFilter returns an "IterableOps.WithFilter" by which we can call map, flatMap, foreach
  val combinations = numbers.withFilter(_ % 2 == 0).flatMap(number => chars.flatMap(char => colors.map(color => s"$number$char - $color")))

  // for-comprehension = IDENTICAL to flatMap + map chains
  // please note: this for is not a for loop you see in other langs, it's an expression. we know that a regular for loop is not an expression
  // every generator will be considered as a flatMap, except the last one, which is considered as a map
  val combinationsFor = for {
    number <- numbers if number % 2 == 0 // generator with "if guard" => "if guard" actually uses "withFilter" method instead of "filter"
    char <- chars // generator
    color <- colors // generator
  } yield {
    // an EXPRESSION
    s"$number$char - $color"
  }

  // for-comprehensions with Unit:
  // by using "yield", we can get a List composed of items returned in the yield section.
  // but what if we want to do something Unit there and don't want to return a List?
  // if the data structure under question has "foreach" method, then we can use below structure:
   for {
    number <- numbers if number % 2 == 0 // generator with "if guard" => "if guard" actually uses "withFilter" method instead of "filter"
    char <- chars // generator
    color <- colors // generator
  } {
    println(s"here:>>>> $number$char - $color")
  }

  // map + flatMap = for comprehensions
  // so every data structure that supports map, flatMap, also supports for comprehensions as well.
  // if we want data structure to support "if guard" as well, then it needs to support withFilter. this method can have the same as filter

  // NOTE: you need to consider when you chain multiple data structures (generators) in a for comprehensions,
  // all of them need to be the same type. i.e. you cannot for example chain a List and a Set as generators,
  // the reason is that compiler wants to chain these generators with flatMao and map.

  /**
   * Exercises
   * 1. LList supports for comprehensions?
   * 2. A small collection of AT MOST ONE element - Maybe[A]
   *  - map
   *  - flatMap
   *  - filter
   */
  import com.rockthejvm.practice.*
  val lSimpleNumbers: LList[Int] = Cons(1, Cons(2, Cons(3, Empty())))
  // map, flatMap, filter
  val incrementedNumbers = lSimpleNumbers.map(_ + 1)
  val filteredNumbers = lSimpleNumbers.filter(_ % 2 == 0)
  val toPairLList: Int => LList[Int] = (x: Int) => Cons(x, Cons(x + 1, Empty()))
  val flatMappedNumbers = lSimpleNumbers.flatMap(toPairLList)

  // map + flatMap = for comprehensions
  val combinationNumbers = for {
    number <- lSimpleNumbers if number % 2 == 0
    char <- Cons('a', Cons('b', Cons('c', Empty())))
  } yield s"$number-$char"

  def main(args: Array[String]): Unit = {
    numbers.foreach(println)
    for {
      num <- numbers
    } println(num)

    println(combinations)
    println(combinationsFor)

    println(incrementedNumbers)
    println(filteredNumbers)
    println(flatMappedNumbers)
    println(combinationNumbers)
  }
}
