package com.rockthejvm.part2oop

object PreventingInheritance_5 {

  class Person(final val name: String) {
    final def enjoyLife(): Int = 42 // final = cannot be overridden
  }

  // we cannot override final val
  class Adult(/*override val*/ name: String) extends Person(name + " hi") {
    // override def enjoyLife() = 999 // illegal
    
    // you cannot access to super's field in the child e.g. super.name
    // if you want to have access, you need to define them as def, not val/var
  }

  final class Animal // cannot be inherited
  // class Cat extends Animal // illegal

  // sealing a type hierarchy = inheritance only permitted inside this file
  sealed class Guitar(nStrings: Int)
  class ElectricGuitar(nStrings: Int) extends Guitar(nStrings)
  class AcousticGuitar extends Guitar(6)

  // in scala, unlike Java, we're not willing to have heavy inheritance & hierarchies
  // so by default we should think not to extend a class,
  // no modifier = "not encouraging" inheritance
  // open = specifically marked for extension
  // not mandatory, good practice (should be accompanied by documentation on what extension implies)
  open class ExtensibleGuitar(nStrings: Int)

  def main(args: Array[String]): Unit = {
    val adult = new Adult("ah")
    println(adult.name)
  }
}
