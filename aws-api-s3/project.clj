(defproject aws-api-s3 "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.cognitect.aws/api       "0.8.456"]
                 [com.cognitect.aws/endpoints "1.1.11.789"]
                 [com.cognitect.aws/s3        "799.2.682.0"]]

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
    "-H:+AllowIncompleteClasspath"
    "--initialize-at-build-time"
    ;;"--initialize-at-run-time=com.amazonaws.auth.DefaultAWSCredentialsProviderChain"
    "--enable-http" "--enable-https" "--enable-all-security-services"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
