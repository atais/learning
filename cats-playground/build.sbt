name := "cats-playground"

version := "0.1"

scalaVersion := "2.12.6"

scalacOptions += "-Ypartial-unification"
scalacOptions += "-language:higherKinds"

libraryDependencies += "org.typelevel" %% "cats-core" % "1.0.1"