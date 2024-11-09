(defproject pedestal "0.1.0-SNAPSHOT"

  :paths ["src"]
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [io.pedestal/pedestal.service "0.7.2"]
                 [io.pedestal/pedestal.jetty "0.7.2"]]

  :main simple.main

  :uberjar-name "simple-main.jar"

  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "/Users/bruno.nascimento/Downloads/graalvm-jdk-23.0.1+11.1/Contents/Home/bin/native-image"
    "--no-fallback"
    "--report-unsupported-elements-at-runtime"
    "--no-server"
    "--allow-incomplete-classpath"
    "--initialize-at-build-time"
    "--enable-url-protocols=http,https"
    "-Dio.pedestal.log.defaultMetricsRecorder=nil"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:ReflectionConfigurationFiles=reflect-config.json"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
