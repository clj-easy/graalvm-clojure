(defproject sample-project "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.11.1"]
                 ;; add the library here
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
    "-H:+TraceNativeToolUsage"
    "-H:+AllowIncompleteClasspath"
    "--verbose"
    "--no-fallback"
    "--report-unsupported-elements-at-runtime"
    ;; add here the namespaces of the library to test separated by commas
    "--initialize-at-build-time=clojure"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
