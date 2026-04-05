name := "tmd-data-ingestion"
version := "0.1.0-SNAPSHOT"
scalaVersion := "3.3.7"

libraryDependencies ++= Seq(
  // HTTP client
  "com.softwaremill.sttp.client3" %% "core" % "3.9.0",
  "com.softwaremill.sttp.client3" %% "circe" % "3.9.0",

  // JSON parsing
  "io.circe" %% "circe-core" % "0.14.6",
  "io.circe" %% "circe-generic" % "0.14.6",
  "io.circe" %% "circe-parser" % "0.14.6",

  // Logging (optional but recommended)
  "ch.qos.logback" % "logback-classic" % "1.4.11"
)

//API: Q1BRWFWW9OB08FSD