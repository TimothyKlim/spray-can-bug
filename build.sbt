name := "test app"

scalaVersion := "2.10.2"

sbtVersion := "0.12.4"

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io",
  "releases"  at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "io.spray" % "spray-can" % "1.1-M8",
  "io.spray" % "spray-client" % "1.1-M8",
  "io.spray" % "spray-io" % "1.1-M8",
  "io.spray" % "spray-http" % "1.1-M8",
  "com.typesafe.akka" %% "akka-actor" % "2.1.4",
  "com.typesafe.akka" %%  "akka-slf4j" % "2.1.4",
  "ch.qos.logback" % "logback-classic" % "1.0.12",
  "org.specs2" %% "specs2" % "1.14" % "test"
)
