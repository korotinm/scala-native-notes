name := "examples"
enablePlugins(ScalaNativePlugin)
scalaVersion := "2.13.4" 
scalacOptions ++= Seq("-feature") 
nativeMode := "debug"
nativeGC := "immix"
