package com.rockthejvm.part2oop

import scala.collection.SortedSet

// in Scala 3 (not available in 2) we can define values and methods at the top-level
// they will be included in a synthetic object with the same name as the package
// can be imported via an mypackage.* import
// we can consider these as global variables/def which are anti-patterns. we better not to use them
val meaningOfLife = 42
def computeMyLife: String = "Scala"

object PackagesImports { // top-level definition
  // packages = form of organization of definitions, similar to a folder structure in a normal file system

  // fully qualified name
  val aList: com.rockthejvm.practice.LList[Int] = ??? // throws NotImplementedError

  // import
  import com.rockthejvm.practice.LList
  val anotherList: LList[Int] = ???

  // importing under an alias
  import java.util.{List as JList} // as was added in Scala 3
//  import java.util.{List => JList} // => was there in Scala 2
  val aJavaList: JList[Int] = ???

  // import everything
  import com.rockthejvm.practice.*
  val aPredicate: Cons[Int] = ???

  // import multiple symbols
  import PhysicsConstants.{SPEED_OF_LIGHT, EARTH_GRAVITY}
  val c = SPEED_OF_LIGHT

  // import everything EXCEPT something
  object PlayingPhysics {
    // here we're excluding PLACK, but everything else will be imported
    import PhysicsConstants.{PLACK as _, *}
    // val plank = PLANK // will not work
  }

  import com.rockthejvm.part2oop.* // import the meaningOfLife and computeMyLife => these have been defined at top-level, outside of the top-level object
  val mol = meaningOfLife

  // default imports:
  // scala.*, scala.Predef.*, java.lang.*

  // exports
  class PhysicsCalculator {
    import PhysicsConstants.*
    def computePhotonEnergy(wavelength: Double): Double =
      PLACK / wavelength
  }

  object ScienceApp {
    val physicsCalculator = new PhysicsCalculator

    // exports create aliases for fields/methods to use locally
    // it means we can use this method by its plain-name, as if it's a first-citizen here
    export physicsCalculator.computePhotonEnergy

    def computeEquivalentMass(wavelength: Double): Double =
      computePhotonEnergy(wavelength) / (SPEED_OF_LIGHT * SPEED_OF_LIGHT)
      // ^^ the computePhotonEnergy method can be used directly (instead of physicsCalculator.computePhotonEnergy)
      // useful especially when these uses are repeated
  }

  def main(args: Array[String]): Unit = {
    // for testing
  }
}

// usually organizing "utils" and constants in separate objects
object PhysicsConstants {
  // constants
  val SPEED_OF_LIGHT = 299792458
  val PLACK = 6.62e-34 // scientific notation
  val EARTH_GRAVITY = 9.8
}