(defproject amazonica-s3 "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [amazonica "0.3.152"
                  :exclusions [com.taoensso/nippy
                               com.amazonaws/aws-java-sdk
                               com.amazonaws/amazon-kinesis-client]]
                 [com.amazonaws/aws-java-sdk-core "1.11.805"]
                 [com.amazonaws/aws-java-sdk-s3 "1.11.805"]
                 [log4j/log4j "1.2.17"]
                 [org.slf4j/slf4j-log4j12 "1.7.30"]
                 [org.slf4j/jul-to-slf4j "1.7.30"]
                 [org.slf4j/jcl-over-slf4j "1.7.30"]]

  :main simple.main

  :uberjar-name "simple-main.jar"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native-config"
   ["shell"
    ;; run the application to infer the build configuration
    "java" "-agentlib:native-image-agent=config-output-dir=./target/config/"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"]


   "native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime" "--no-server"
    "-H:+PrintClassInitialization"
    "-H:ConfigurationFileDirectories=./target/config/"
    "--initialize-at-build-time"
    "--initialize-at-run-time=com.amazonaws.auth.DefaultAWSCredentialsProviderChain"
    "--enable-http" "--enable-https" "--enable-all-security-services"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
