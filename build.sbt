name := "reacKafka"

version := "1.0"

scalaVersion := "2.11.8"


val akkaVersion = "2.5.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
//  "com.typesafe.akka" %% "akka-stream-kafka" % "0.13",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.16-SNAPSHOT",
  "org.apache.commons" % "commons-math3" % "3.0",
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion
)