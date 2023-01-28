package com.rockthejvm.practice

import scala.annotation.tailrec

object MapsExercises {

  /**
   * Social network = Map[String, Set[String]]
   * Friend relationships are MUTUAL.
   *
   * - add a person to the network
   * - remove a person from the network
   * - add friend relationship
   * - unfriend
   *
   * - number of friends of a person
   * - who has the most friends
   * - how many people have NO friends
   * + if there is a social connection between two people (direct or not)
   *
   * Daniel <-> Mary <-> Jane <-> Tom
   */

  def addPerson(network: Map[String, Set[String]], newPerson: String): Map[String, Set[String]] =
    network + (newPerson -> Set())

  def removePerson(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    (network - person).map(pair => pair._1 -> (pair._2 - person))

  def removePersonV2(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    @tailrec
    def removeFromConnections(network: Map[String, Set[String]], connections: Set[String]): Map[String, Set[String]] = {
      if (connections.isEmpty) network
      else {
        val connection = connections.head
        val networkWithoutConnectedPerson = network + (connection -> (network(connection) - person))
        removeFromConnections(networkWithoutConnectedPerson, connections.tail)
      }
    }

    val updatedNetwork = removeFromConnections(network, network(person))
    updatedNetwork - person
  }

  def makeFriend(network: Map[String, Set[String]], firstPerson: String, secondPerson: String): Map[String, Set[String]] = {
    val updatedNet = network + (firstPerson -> (network(firstPerson) + secondPerson))
    updatedNet + (secondPerson -> (updatedNet(secondPerson) + firstPerson))
  }

  def removeFriend(network: Map[String, Set[String]], firstPerson: String, secondPerson: String): Map[String, Set[String]] = {
    val updatedNet = network + (firstPerson -> (network(firstPerson) - secondPerson))
    updatedNet + (secondPerson -> (updatedNet(secondPerson) - firstPerson))
  }

  def numberOfFriends(network: Map[String, Set[String]], person: String): Int = network(person).size

  def findPersonWithMaxFriend(network: Map[String, Set[String]]): String =
    network.max((a, b) => a._2.size - b._2.size)._1

  def findPersonWithMaxFriendV2(network: Map[String, Set[String]]): String = {
    // the actual signature of Map.foldLeft: foldLeft(initialValue)(lambda)
    // we can simplify the lambda (second parentheses) as below:
    val best = network.foldLeft(("", -1)) /* parenthesis has been replaced with a {} */ { (currentBest, pair) => // we don't need to put a {} here anymore
      val currentMostPopularPerson = currentBest._1
      val mostFriendsSoFar = currentBest._2

      val newPerson = pair._1
      val newPersonFriends = pair._2.size

      if (mostFriendsSoFar < newPersonFriends) (newPerson, newPersonFriends)
      else currentBest
    }
    best._1
  }


  def peopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(tuple => tuple._2.isEmpty)

  def degreeOfSeparationConnection(network: Map[String, Set[String]], firstPerson: String, secondPerson: String): Int = {
    if (!network.contains(firstPerson) || !network.contains(secondPerson)) throw new IllegalArgumentException("no such a person")

    @tailrec
    def visit(visitedPersons: Set[String], visitingPersons: Set[String], separationDegree: Int, target: String): Int = {
      if (visitingPersons.isEmpty) -1
      else if (visitingPersons.contains(target)) separationDegree
      else {
        val newVisitedPersons = visitedPersons ++ visitingPersons
        val newVisitingPersons = visitingPersons.filter(!visitedPersons.contains(_))
          .flatMap(network(_))
        visit(newVisitedPersons, newVisitingPersons, separationDegree + 1, target)
      }
    }

    visit(Set(), network(firstPerson), 0, secondPerson)
  }

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    // Breadth-first search
    @tailrec
    def search(discoveredPeople: Set[String], consideredPeople: Set[String]): Boolean =
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        val personsFriends = network(person)

        if (personsFriends.contains(b)) true
        else search(discoveredPeople - person ++ personsFriends -- consideredPeople, consideredPeople + person)
      }

    if (!network.contains(a) || !network.contains(b)) false
    else search(network(a), Set(a))
  }


  def main(args: Array[String]): Unit = {
    val empty: Map[String, Set[String]] = Map()
    val people = makeFriend(makeFriend(addPerson(addPerson(addPerson(addPerson(empty, "Bob"), "Mary"), "Jim"), "Andy"), "Bob", "Mary"), "Bob", "Jim")


    println("people:> " + people)
    println("removePerson:> " + removePerson(people, "Bob"))
    println("removeFriend:> " + removeFriend(people, "Bob", "Mary"))
    println("findPersonWithMaxFriend:> " + findPersonWithMaxFriend(people))
    println("peopleWithNoFriends:> " + peopleWithNoFriends(people))
    println("degreeOfSeparationConnection" + degreeOfSeparationConnection(people, "Jim", "Mary"))
  }

}
