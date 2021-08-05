name := """DatabaseModel"""
organization := "databasemodel"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "com.vmunier" % "scalajs-scripts_2.13" % "1.2.0",
  guice,
  jdbc,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.slick" % "slick-codegen_2.13" % "3.3.3",
  "com.typesafe.play" %% "play-json" % "2.8.1",
  "org.postgresql" % "postgresql" % "42.2.11",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
  "org.mindrot" % "jbcrypt" % "0.4",
  specs2 % Test
)

libraryDependencies ++= Seq(
  javaCore,
  // Add your project dependencies here
  "org.elasticsearch" % "elasticsearch" % "2.1.1",
  "org.codehaus.groovy" % "groovy-all" % "2.3.8",
  "org.apache.commons" % "commons-lang3" % "3.1",
  "org.easytesting" % "fest-assert" % "1.4" % "test",
  //"org.specs2" % "specs2-core_2.13" % "4.12.4-js-ec"
)
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "databasemodel.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "databasemodel.binders._"
