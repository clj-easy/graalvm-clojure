(defproject byte-streams "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clj-commons/byte-streams "0.2.11"]
                 ]

  :main simple.main

  :uberjar-name "simple-main.jar"

  :profiles {:uberjar {:aot :all}
             :graal-build-time [:uberjar {:dependencies [[com.github.clj-easy/graal-build-time "0.1.4"]]}]
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
            {"native"
             ["shell"
              "native-image" "--report-unsupported-elements-at-runtime" "--no-server" "--no-fallback"
              "--initialize-at-build-time"
              "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
              "-H:Name=./target/${:name}"
              ]

             "uberjar+graal-build-time"
             ["with-profile" "+graal-build-time" "uberjar"]

             "native+graal-build-time"
             ["shell"
              "native-image" "--report-unsupported-elements-at-runtime" "--no-fallback"
              "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
              "-H:Name=./target/${:name}"
              ]

             "run-native" ["shell" "./target/${:name}"]})
