ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.6.2"

lazy val root = (project in file("."))
  .settings(
    name := "newActors"
  )
resolvers += "Akka library repository".at("https://repo.akka.io/maven")
val AkkaVersion = "2.10.0"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.10.0",
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % "2.10.0" % Test
)