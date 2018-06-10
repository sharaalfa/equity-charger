import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, defaultScalariformSettings}
import scalariform.formatter.preferences.{DoubleIndentClassDeclaration, FormatXml, PreserveDanglingCloseParenthesis}

name := """equity-charger"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
resolvers := ("Atlassian Releases" at "https://maven.atlassian.com/public/") +: resolvers.value
resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += Resolver.sonatypeRepo("snapshots")
/*resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
resolvers += Resolver.defaultLocal
resolvers += Resolver.mavenLocal
resolvers += "Local Maven Repository" at "file:///"+Path.userHome.absolutePath+"/.m2/repository"
resolvers +=  "apache-snapshots" at "http://repository.apache.org/snapshots/"
resolvers += "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases"
resolvers += "Atlassian Releases" at "https://maven.atlassian.com/public/"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += Resolver.jcenterRepo
resolvers += Resolver.sonatypeRepo("snapshots")*/
      
scalaVersion := /*"2.12.2""2.11.7"*/ "2.11.8"

/*libraryDependencies ++= Seq(ehcache , ws , specs2 % Test, filters, evolutions,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "org.postgresql" % "postgresql" % "42.2.2",
  "com.typesafe.play" %% "anorm" % "2.5.3",
  "com.github.tminglei" %% "slick-pg" % "0.16.0",
  "com.typesafe.play" %% "play-slick" % "3.0.2",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.2",
  "com.github.tminglei" %% "slick-pg_play-json" % "0.16.0",
  "com.typesafe.play" %% "play-logback" % "2.6.13",
  "com.typesafe.play" %% "filters-helpers" % "2.6.13",
  //"org.apache.spark" %% "spark-core" % "2.3.0",
 // "org.apache.spark" %% "spark-sql" % "2.3.0",
  "com.typesafe.akka" %% "akka-actor" % "2.5.9",
  "com.typesafe.akka" %% "akka-slf4j" % "2.5.9",
  "com.mohiva" %% "play-silhouette" % "3.0.2",
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "net.codingwell" %% "scala-guice" % "4.0.0",
  "net.ceedubs" %% "ficus" % "1.1.2",
  "com.adrianhurt" %% "play-bootstrap3" % "0.4.4-P24",
  "com.mohiva" %% "play-silhouette-testkit" % "3.0.2" % "test"

)*/
libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "42.2.2",
  "com.mohiva" %% "play-silhouette" % "3.0.2",
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "net.codingwell" %% "scala-guice" % "4.0.0",
  "net.ceedubs" %% "ficus" % "1.1.2",
  "com.adrianhurt" %% "play-bootstrap3" % "0.4.4-P24",
  "com.mohiva" %% "play-silhouette-testkit" % "3.0.2" % "test",
  specs2 % Test,
  "com.typesafe.play" %% "play-slick" % "1.0.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.0.1",
  cache,
  evolutions,
  filters
)



libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )
routesGenerator := InjectedRoutesGenerator

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xfatal-warnings", // Fail the compilation if there are any warnings.
  "-Xlint", // Enable recommended additional warnings.
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
  "-Ywarn-numeric-widen" // Warn when numerics are widened.
)
unmanagedBase := baseDirectory.value / "lib"
//********************************************************
// Scalariform settings
//********************************************************

defaultScalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(FormatXml, false)
  .setPreference(DoubleIndentClassDeclaration, false)
  .setPreference(PreserveDanglingCloseParenthesis, true)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
herokuAppName in Compile := "equity-charger"