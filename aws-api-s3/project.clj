(defproject aws-api-s3 "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.12.0"]
                 [com.cognitect.aws/api "0.8.692"]
                 [com.cognitect.aws/endpoints "1.1.12.772"]
                 [com.cognitect.aws/s3 "869.2.1687.0"]
                 [com.github.clj-easy/graal-build-time "1.0.5"]]

  :main simple.main

  :uberjar-name "simple-main.jar"
  :profiles {:uberjar {:aot :all}
             :dev     {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image"
    "--no-fallback"
    "--report-unsupported-elements-at-runtime"
    "--no-server"
    "--allow-incomplete-classpath"
    "--initialize-at-build-time"
    "-H:+PrintClassInitialization"
    "--features=clj_easy.graal_build_time.InitClojureClasses"

    "-H:IncludeResources='.*/service.*edn'"

    "--enable-http" "--enable-https" "--enable-all-security-services"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
