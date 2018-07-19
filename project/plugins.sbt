logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += Resolver.bintrayRepo("kamon-io", "sbt-plugins")
resolvers += Resolver.bintrayIvyRepo("kamon-io", "sbt-plugins")


addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.12")
addSbtPlugin("io.kamon" % "sbt-aspectj-runner" % "1.1.0")
addSbtPlugin("io.kamon" % "sbt-aspectj-runner-play-2.6" % "1.1.0")