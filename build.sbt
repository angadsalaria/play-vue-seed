name := "playtemplate"
 
version := "1.0" 
      
lazy val `playtemplate` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"


scalaVersion := "2.11.7"

libraryDependencies ++= Seq( specs2 % Test , guice,
  "io.kamon" %% "kamon-play-2.6" % "1.1.0",
  "io.kamon" %% "kamon-core" % "1.1.0",
  "io.kamon" %% "kamon-prometheus" % "1.0.0"
)





unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      