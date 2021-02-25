name := "examples"
enablePlugins(ScalaNativePlugin)
scalaVersion := "2.11.12" 
scalacOptions ++= Seq("-feature") 
nativeMode := "release"
//nativeMode := "debug"
nativeGC := "immix"
