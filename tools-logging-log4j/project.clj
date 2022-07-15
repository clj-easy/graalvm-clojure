(defproject tools-logging "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/tools.logging "0.4.1"]
                 ;; also SLF4j works with this configuration
                 ;;[org.slf4j/slf4j-log4j12 "1.7.36"]
                 [org.apache.logging.log4j/log4j-core "2.18.0"]
                 [com.github.clj-easy/graal-build-time "0.1.4"]]

  :main simple.main

  :uberjar-name "simple-main.jar"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {
   "native-config"
   ["shell"
    "java" "-agentlib:native-image-agent=config-merge-dir=./graalvm-config"
    "-jar" "target/simple-main.jar"]


   "native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime"
    "--no-fallback" "--allow-incomplete-classpath"
    "-H:Log=registerResource:"
    "-H:ConfigurationFileDirectories=./graalvm-config/"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
