name := "type-level-demo"

version := "0.1"

scalaVersion := "2.13.8"


val catsEffectVersion = "3.3.14"

libraryDependencies += "org.typelevel" %% "cats-core" % "2.8.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % catsEffectVersion

// available for 2.12, 2.13, 3.0
libraryDependencies += "co.fs2" %% "fs2-core" % "3.2.9"

// optional I/O library
libraryDependencies += "co.fs2" %% "fs2-io" % "3.2.9"

// optional reactive streams interop
libraryDependencies += "co.fs2" %% "fs2-reactive-streams" % "3.2.9"

// optional scodec interop
libraryDependencies += "co.fs2" %% "fs2-scodec" % "3.2.9"

