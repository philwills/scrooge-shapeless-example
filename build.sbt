scalaVersion := "2.11.6"

com.twitter.scrooge.ScroogeSBT.newSettings

libraryDependencies ++= Seq(
  "org.apache.thrift" % "libthrift" % "0.8.0",
  "com.twitter" %% "scrooge-core" % "3.17.0",
  "com.chuusai" %% "shapeless" % "2.1.0"
)
