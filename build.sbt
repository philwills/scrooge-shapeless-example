scalaVersion := "2.11.6"

com.twitter.scrooge.ScroogeSBT.newSettings

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies ++= Seq(
  "org.apache.thrift" % "libthrift" % "0.8.0",
  "com.twitter" %% "scrooge-core" % "3.17.0",
  "com.chuusai" %% "shapeless" % "2.3.0-SNAPSHOT"
)
