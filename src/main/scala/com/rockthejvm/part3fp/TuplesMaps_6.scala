package com.rockthejvm.part3fp

object TuplesMaps_6 {

  // tuples = finite ordered "lists" / group of values under the same "big" value
  val aTuple = (2, "rock the jvm") // Tuple2[Int, String] == (Int, String)
  val firstField = aTuple._1
  val aCopiedTuple = aTuple.copy(_1 = 54)

  // tuples can have more than two elements:
  val tuples = (2, "rock the jvm", "hello", "world", 5)

  // tuples of 2 elements
  val aTuple_v2 = 2 -> "rock the jvm" // IDENTICAL to (2, "rock the jvm")

  // maps: keys -> values
  val aMap = Map()

  val phonebook: Map[String, Int] = Map(
    "Jim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123,
  )

  // core APIs
  val phonebookHasDaniel = phonebook.contains("Daniel")
  val marysPhoneNumberOption = phonebook.get("Mary") // None
  // val marysPhoneNumber = phonebook("Mary") // crash with an exception, because "Mery" is not present in the Map

  // in order not to crash when a key is not present in the map, we can use "withDefaultValue" while creating a Map:
  val aSafePhonebook = phonebook.withDefaultValue(-1)
  val marysPhoneNumberSafe = aSafePhonebook("Mary") // -1: default value

  // add a pair
  val newPair = "Mary" -> 678
  val newPhonebook = phonebook + newPair // new map

  // remove a key
  val phoneBookWithoutDaniel = phonebook - "Daniel" // new map

  // list -> map
  val linearPhonebook = List(
    "Jim" -> 555,
    "Daniel" -> 789,
    "Jane" -> 123
  )
  val phonebook_v2 = linearPhonebook.toMap

  // map -> linear collection
  val linearPhonebook_v2 = phonebook.toList // toSeq, toVector, toArray, toSet
  // if you have two association for a key (for example "Daniel), then the last association will be kept in the map

  // map, flatMap, filter
  val aProcessedPhonebook = phonebook.map(pair => (pair._1.toUpperCase(), pair._2))
  // this map and flatmap can be a little dangerous in maps
  // assume you have this: Map("Jim" -> 123, "jiM" -> 999) and now you want to uppercase keys: => Map("JIM" -> ????)
  // as you can see one of the associations will be removed and we cannot be sure which one it is (Map is not ordered)

  // filtering keys
  val noJs = phonebook.view.filterKeys(!_.startsWith("J")).toMap
  val noJs_v2 = phonebook.filter(!_._1.startsWith("J"))

  // mapping values => keys are kept untouched
  val prefixNumbers = phonebook.view.mapValues(number => s"0255-$number").toMap

  // other collections can create maps
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  val nameGroupings = names.groupBy(name => name.charAt(0)) // Map[Char, List[String]]


  def main(args: Array[String]): Unit = {
    println(phonebook)
    println(phonebookHasDaniel)
    println(marysPhoneNumberSafe)
    println(nameGroupings)
  }

}
