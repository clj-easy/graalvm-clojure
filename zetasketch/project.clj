(defproject sample-project "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.11.1"]
                 ;; add the library here
                 [com.google.zetasketch/zetasketch "0.1.0"]
                 ]

  :main simple.main

  :uberjar-name "simple-main.jar"

  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image"
    "-Ob"
    "-H:ReflectionConfigurationFiles=./resources/META-INF/native-image/com.google/zetasketch/reflect-config.json"
    "-H:+TraceNativeToolUsage"
    "-H:+AllowIncompleteClasspath"
    "--verbose"
    "--no-fallback"
    "--report-unsupported-elements-at-runtime"
    "--initialize-at-build-time=clojure,simple"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
