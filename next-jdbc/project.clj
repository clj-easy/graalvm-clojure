(defproject next.jdbc "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [seancorfield/next.jdbc "1.0.409"]
                 [org.postgresql/postgresql "42.2.11"]
                 [honeysql "0.9.10"]]

  :main simple.main

  :uberjar-name "simple-main.jar"

  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" 
    "--report-unsupported-elements-at-runtime" 
    "--no-server"
    "--initialize-at-build-time"
    "--enable-url-protocols=http,https"
    "--initialize-at-run-time=org.postgresql.sspi.SSPIClient"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
