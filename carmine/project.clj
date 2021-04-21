(defproject sample-project "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.10.3"]
                 [com.taoensso/carmine "3.1.0"]
                 ]

  :main simple.main

  :uberjar-name "simple-main.jar"

  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime" "--no-server" "--no-fallback"
    "--initialize-at-build-time"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"
    "-H:ConfigurationFileDirectories=./graalvm-config/"]

   "run-native" ["shell" "./target/${:name}"]})

