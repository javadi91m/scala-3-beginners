package com.rockthejvm.part2oop

object Enums_12 {

  // first class support for enums has been added in scala 3
  // we can consider enum as a datatype that have only a bunch of instances that we create within enum itself
  // these "only possible instances" are created by using "case" keyword
  // enums are sealed, so we cannot extend them further outside of the file
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // enums are datatype, so we can add fields/methods
    def openDocument(): Unit =
      if (this == READ) println("opening document...")
      else println("reading not allowed.")
  }

  val somePermissions: Permissions = Permissions.READ

  // enums can take constructor arguments
  enum PermissionsWithBits(bits: Int) {
    // these cases need to be instantiated early, so we need to pass the constructor args
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  // enums can have a companion object
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = // whatever
      PermissionsWithBits.NONE
  }

  // standard API
  val somePermissionsOrdinal = somePermissions.ordinal // 0 => Permissions.READ has been defined as index 0 (first defined enum)
  val allPermissions = PermissionsWithBits.values // array of all possible values of the enum
  val readPermission: Permissions = Permissions.valueOf("READ") // Permissions.READ

  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionsOrdinal)
  }
}
