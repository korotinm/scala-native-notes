name := "examples"
enablePlugins(ScalaNativePlugin)
scalaVersion := "2.11.12" 
scalacOptions ++= Seq("-feature") 
nativeMode := "release-full"
nativeLTO := "thin"
nativeGC := "immix"
