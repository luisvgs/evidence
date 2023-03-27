val scala3Version = "3.1.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "evidence",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "com.github.tototoshi" %% "scala-csv" % "1.3.10",
      "com.github.losizm" %% "t2" % "2.0.0",
      "com.lihaoyi" %% "fansi" % "0.4.0"
    )
  )
