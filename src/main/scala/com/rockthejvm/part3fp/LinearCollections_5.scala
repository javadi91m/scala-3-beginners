package com.rockthejvm.part3fp

import scala.util.Random

object LinearCollections_5 {

  // Seq = well-defined ordering + indexing
  def testSeq(): Unit = {
    // Seq itself is a trait and when we create a Seq like below, we're not sure which implementation will be used
    val aSequence = Seq(4, 2, 3, 1)

    // although Seq is an ordered list, but it also supports head and tail APIs:
    aSequence.head
    aSequence.tail

    // appending and prepending => 0 will be added to the beginning of the list and 4 will be added to the end of the list
    // NOTE: + sign resides at the side of the element that we want to add it.
    val aBiggerSeq = 0 +: aSequence :+ 4

    // main API: index an element => equivalent to .get method on Java List
    val thirdElement = aSequence.apply(2) // element 3

    // map/flatMap/filter/for
    val anIncrementedSequence = aSequence.map(_ + 1) // [5,3,4,2]
    val aFlatMappedSequence = aSequence.flatMap(x => Seq(x, x + 1)) // [4,5,2,3,3,4,1,2]
    val aFilteredSequence = aSequence.filter(_ % 2 == 0) // [4,2]

    // other methods
    val reversed = aSequence.reverse
    val concatenation = aSequence ++ Seq(5, 6, 7)

    // sort method takes an implicit Ordering that has been already defined for some well-known types (e.g. Int, String, ...)
    val sortedSequence = aSequence.sorted // [1,2,3,4]
    val customSorted = aSequence.sorted((a, b) => b - a)
    println(s"customSorted: $customSorted")

    // foldLeft takes an initial value as the first input of curried function
    val sum = aSequence.foldLeft(0)(_ + _) // 10
    val stringRep = aSequence.mkString("[", ",", "]")

    println(aSequence)
    println(concatenation)
    println(sortedSequence)
  }

  // lists
  // List is an implementation of Seq
  def testLists(): Unit = {
    val aList = List(1, 2, 3)
    // same API as Seq
    val thirdElement = aList.apply(2)

    val firstElement = aList.head
    val rest = aList.tail
    // appending and prepending => 0 will be added to the beginning of the list and 4 will be added to the end of the list
    val aBiggerList = 0 +: aList :+ 4

    // adding an element to the BEGINNING of the List
    val prepending = 0 :: aList // :: equivalent to Cons in our LList

    // utility methods => ["Scala", "Scala", "Scala", "Scala", "Scala"]
    val scalax5 = List.fill(5)("Scala")
  }

  // ranges
  def testRanges(): Unit = {

    // a Range is not necessarily evaluated all at once at the creation time, you can think of it as a lazy Seq
    // it means Scala does not hold all "Int.MaxValue" numbers in memory
    val aRange = 1 to Int.MaxValue

    // OR we can explicitly assign type: Seq to it
    //    val aRange: Seq[Int] = 1 to 10
    val aNonInclusiveRange = 1 until 10 // 10 not included
    // same Seq API
    (1 to 10).foreach(_ => println("Scala"))
  }

  // arrays
  def testArrays(): Unit = {
    val anArray = Array(1, 2, 3, 4, 5, 6) // int[] on the JVM
    // most Seq APIs
    // arrays are not Seqs
    val aSequence: Seq[Int] = anArray.toIndexedSeq

    // arrays are MUTABLE. first argument below is the index
    anArray.update(2, 30) // no new array is allocated
  }

  // vectors = fast Seqs for a large amount of data
  def testVectors(): Unit = {
    val aVector: Vector[Int] = Vector(1, 2, 3, 4, 5, 6)
    // the same Seq API
  }

  def smallBenchmark(): Unit = {
    val maxRuns = 1000
    val maxCapacity = 1000000

    def getWriteTime(collection: Seq[Int]): Double = {
      val random = new Random()
      val times = for {
        i <- 1 to maxRuns
      } yield {
        val index = random.nextInt(maxCapacity)
        val element = random.nextInt()

        val currentTime = System.nanoTime()
        val updatedCollection = collection.updated(index, element)
        System.nanoTime() - currentTime
      }

      // compute average
      times.sum * 1.0 / maxRuns
    }

    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector

    println(getWriteTime(numbersList))
    println(getWriteTime(numbersVector))
  }

  // sets
  def testSets(): Unit = {
    // a popular implementation is HashSet
    val aSet = Set(1, 2, 3, 4, 5, 4) // no ordering guaranteed
    // equals + hashCode = hash set
    // main API: test if in the set
    val contains3 = aSet.contains(3) // true
    val contains3_v2 = aSet(3) // same: true

    // adding/removing
    // adding an element to the Set always returns a new Set, regardless of that element has been actually added to the Set or not (because of duplication)
    val aBiggerSet = aSet + 4 // [1,2,3,4,5]
    val aSmallerSet = aSet - 4 // [1,2,3,5]
    // concatenation == union
    val anotherSet = Set(4, 5, 6, 7, 8)

    // all are the same
    val muchBiggerSet = aSet.union(anotherSet)
    val muchBiggerSet_v2 = aSet ++ anotherSet // same
    val muchBiggerSet_v3 = aSet | anotherSet // same

    // difference
    val aDiffSet = aSet.diff(anotherSet)
    val aDiffSet_v2 = aSet -- anotherSet // same

    // intersection => all the common elements of two Sets
    val anIntersection = aSet.intersect(anotherSet) // [4,5]
    val anIntersection_v2 = aSet & anotherSet // same
  }

  def main(args: Array[String]): Unit = {
    testSeq()
    testRanges()
  }
}
