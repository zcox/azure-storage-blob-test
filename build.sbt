scalaVersion := "2.13.6"

lazy val V = new {
  val azureStorageBlob = "12.11.1"
  val munit = "0.7.26"
  val munitCatsEffect2 = "1.0.3"
  val scalacheck = "1.15.4"
  val scalacheckEffect = "1.0.2"
  val scalacheckMagnolia = "0.4.0"
  val log4cats = "1.3.1"
}

libraryDependencies ++= Seq(
  "com.azure" % "azure-storage-blob" % V.azureStorageBlob,
  "org.scalameta" %% "munit" % V.munit,
  "org.scalameta" %% "munit-scalacheck" % V.munit,
  "org.typelevel" %% "munit-cats-effect-2" % V.munitCatsEffect2,
  "org.typelevel" %% "scalacheck-effect-munit" % V.scalacheckEffect,
  "org.scalatest" %% "scalatest" % "3.2.9",
  "org.scalatestplus" %% "scalacheck-1-15" % "3.2.9.0",
  "org.scalacheck" %% "scalacheck" % V.scalacheck,
  "com.github.alexarchambault" %% "scalacheck-shapeless_1.14" % "1.2.5",
  "com.github.chocpanda" %% "scalacheck-magnolia" % V.scalacheckMagnolia,
  "org.typelevel" %% "log4cats-noop" % V.log4cats,
)
