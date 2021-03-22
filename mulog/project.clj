(defproject sample-project "0.1.0"

  :dependencies [[org.clojure/clojure "1.10.3"]
                 [com.brunobonacci/mulog "0.7.0-SNAPSHOT"]
                 ;; add the library here
                 ]

  :main simple.main

  :uberjar-name "simple-main.jar"

  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime" "--no-server" "--no-fallback"
    "--trace-object-instantiation=java.lang.Thread"
    "--initialize-at-build-time"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
