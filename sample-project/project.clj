(def graalvm-home (System/getenv "GRAALVM_HOME"))

(defproject sample-project "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.2-rc1"]
                 ;; add the library here
                 ]
  :main simple.main
  :uberjar-name "simple-main.jar"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}
  :aliases
  {"native-config"
   ["shell"
    ;; run the application to infer the build configuration
    "${:graalvm-home}/bin/java"
    "-agentlib:native-image-agent=config-output-dir=./target/config/"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"]

   "native"
   ["shell"
    "native-image"
    "--report-unsupported-elements-at-runtime"
    "--no-server"
    "--initialize-at-build-time"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native"
   ["shell" "./target/${:name}"]})
