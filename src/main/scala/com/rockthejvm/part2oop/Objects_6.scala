package com.rockthejvm.part2oop

import com.rockthejvm.part2oop.Objects_6.Person.canFly

// object is singleton pattern in scala => only one possible instance of a type will be present through entire application
object Objects_6 {

  object MySingleton { // type + the only instance of this type
    val aField = 45
    def aMethod(x: Int) = x + 1
  }

  val theSingleton = MySingleton
  val anotherSingleton = MySingleton
  val isSameSingleton = theSingleton == anotherSingleton // true
  // objects can have fields and methods
  val theSingletonField = MySingleton.aField
  val theSingletonMethodCall = MySingleton.aMethod(99)

  class Person(val name: String) {
    def sayHi(): String = s"Hi, my name is $name"

    override def equals(obj: Any): Boolean = this.name == obj.asInstanceOf[Person].name
  }

  // companions = class + object with the same name in the same file
  object Person { // companion object
    // class can access object's private fields and methods
    val N_EYES = 2
    def canFly(): Boolean = false
  }

  // methods and fields in classes are used for instance-dependent functionality
  val mary = new Person("Mary")
  val mary22 = mary
  val mary_v2 = new Person("Mary")
  val marysGreeting = mary.sayHi()
  mary == mary

  // methods and fields in objects are used for instance-independent functionality - "static"
  val humansCanFly = Person.canFly()
  val nEyesHuman = Person.N_EYES
  
  // equality
  // 1 - equality of reference - in Java defined as == => in scala == is the same as .equals, but it handles nulls better than equals
   val sameMary = mary eq mary_v2 // false, different instances
  val sameSingleton = MySingleton eq MySingleton // true
  // 2 - equality of "sameness" - in Java defined as .equals
  val sameMary_v2 = mary equals mary_v2 // false
  val sameMary_v3 = mary == mary_v2 // same as equals - false
  val sameSingleton_v2 = MySingleton == MySingleton // true

  // difference between == , equals, and eq
  // You normally use ==, it routes to equals, except that it treats nulls properly. Reference equality (rarely used) is eq.

  // objects can extend classes ==> we need to specify constructor's fields
  object BigFoot extends Person("Big Foot")

  //
  /*
    Scala application:
      object Objects {
        def main(args: Array[String]): Unit = { ... }
      }

    Equivalent Java application:
      public class Objects {
        public static void main(String[] args) { ... }
      }
   */
  def main(args: Array[String]): Unit = {
    println(sameMary_v3)
    println(mary eq mary22)
    println(mary equals mary22)
    println(mary == mary22)

    println(mary eq mary_v2)
    println(mary equals mary_v2)
    println(mary == mary_v2)
  }
}
