package com.rockthejvm.part2oop

object AccessModifiers_4 {

  class Person(val name: String) {
    // protected == access to inside the class + children classes
    protected def sayHi(): String = s"Hi, my name is $name."

    // private = only accessible inside the class
    private def watchNetflix(): String = "I'm binge watching my favorite series..."
  }

  // here age will be a private val
  // if name wasn't a public in Person class, we could define it here without "override"
  // "override" means roughly "here I am declaring something that is already declared in the parent class (and is accessible here) and do not intend to introduce something that isn't inherited"
  class Kid(override val name: String, age: Int) extends Person(name) {
    def greetPolitely(): String = // no modifier = "public"
      sayHi() + "I love to play!"
  }

  val aPerson = new Person("Alice")
  val aKid = new Kid("David", 5)

  // complication
  class KidWithParents(override /*it'll be private since it has val before it*/ val name: String
                       , /*it'll be private since it doesn't have val/var before it*/ age: Int
                       , momName: String
                       , dadName: String) extends Person(name) {
    val mom = new Person(momName) // it'll be public
    val dad = new Person(dadName) // it'll be public

    // can't call a protected method on ANOTHER instance of Person

    //    def everyoneSayHi(): String =
    //      this.sayHi() + s"Hi, I'm $name, and here are my parents: " + mom.sayHi() + dad.sayHi()
  }

  def main(args: Array[String]): Unit = {
    println(aKid.greetPolitely())
    val kidWithParents = new KidWithParents("Bob", 10, "Kate", "Blake")
    println(kidWithParents.dad)
  }
}
