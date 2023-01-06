package com.rockthejvm.part2oop

object AbstractDataTypes_7 {

  abstract class Animal {
    // we don't need to mention "abstract" before method and fields in an abstract class. we just need to leave them unimplemented
    // we can have abstract fields as well.
    val creatureType: String // abstract
    def eat(): Unit
    // non-abstract fields/methods allowed
    def preferredMeal: String = "anything" // "accessor methods" => a method without any arguments or even parentheses 
    // ==> previously we said that as a convention, we write getter-like methods (that do not do any actions) without parentheses
  }

  // abstract classes can't be instantiated
  // val anAnimal: Animal = new Animal

  // non-abstract classes must implement the abstract fields/methods
  class Dog extends Animal {
    override val creatureType = "domestic"
    override def eat(): Unit = println("crunching this bone")
    
    // in Scala we can override a method with a value, only applicable for methods without arguments or even parentheses
    // the return type of the method and value type must be the same
    override val preferredMeal: String = "bones" // overriding accessor method with a field
  }

  val aDog: Animal = new Dog

  // traits => describe behavior
  trait Carnivore { // Scala 3 - traits can have constructor args
    def eat(animal: Animal): Unit
  }

  class TRex extends Carnivore {
    override def eat(animal: Animal): Unit = println("I'm a T-Rex, I eat animals")
  }

  // practical difference abstract classes vs traits
  // one class inheritance
  // multiple traits inheritance
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType = "croc"
    override def eat(): Unit = println("I'm a croc, I just crunch stuff")
    override def eat(animal: Animal): Unit = println("croc eating animal")
  }

  /*
    philosophical difference abstract classes vs traits
    - abstract classes are THINGS => Crocodile IS AN Animal
    - traits are BEHAVIORS  => Carnivores are Animals that EAT meat!
   */

  
  /*
  DataTypes hierarchy in Scala
    Any
      AnyRef
        All classes we write
          scala.Null (the null reference)
      AnyVal
        Int, Boolean, Char, ...


              scala.Nothing ==> Nothing can be replaced with anything (even Null) but it cannot be instantiated, the only way to produce it is to throw an exception
   */

  val aNonExistentAnimal: Animal = null
//  val anInt: Int = throw new NullPointerException // throwing Exceptions return Nothing, so we can write "throw Exception" at the right-hand side of anything

  def main(args: Array[String]): Unit = {

  }
  
}
