(defproject amazonica-s3 "0.1.0-SNAPSHOT"

  :dependencies [#_[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojure "1.8.0"]
                 [amazonica "0.3.142"
                  :exclusions [com.taoensso/nippy
                               com.amazonaws/aws-java-sdk
                               com.amazonaws/amazon-kinesis-client]]
                 [com.amazonaws/aws-java-sdk-core "1.11.546"]
                 [com.amazonaws/aws-java-sdk-s3 "1.11.546"]]

  :main simple.main

  :uberjar-name "simple-main.jar"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime"
    "--initialize-at-build-time"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
