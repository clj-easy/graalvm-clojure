(defproject sample-project "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [sats/upit "0.0.1-SNAPSHOT"]
                 ]

  :repositories
  [["sats" {:url "https://maven.pkg.github.com/sathyavijayan/upit"
            :username :env/GH_PACKAGES_USR
            :password :env/GH_PACKAGES_PSW}]]

  :main simple.main

  :uberjar-name "simple-main.jar"

  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime" "--no-server" "--no-fallback"
    "--initialize-at-build-time"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
