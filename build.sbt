name := """website-traffic"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies ++= Seq(
 guice,
 javaJdbc,
 jdbc,
 "com.h2database" % "h2" % "1.4.196",
 "org.mongodb" % "mongo-java-driver" % "3.2.2",
 "org.assertj" % "assertj-core" % "3.6.2" % Test,
 "org.awaitility" % "awaitility" % "2.0.0" % Test,
 //"org.avaje.ebeanorm" % "avaje-ebeanorm" % "8.1.1",
 "com.google.inject" % "guice" % "4.2.0",
 
 "mysql" % "mysql-connector-java" % "5.1.36"
)
// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

// Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
EclipseKeys.preTasks := Seq(compile in Compile)

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java           // Java project. Don't expect Scala IDE
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)  // Use .class files instead of generated .scala files for views and routes