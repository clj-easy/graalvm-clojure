(defproject sqlite "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.12.0"]
                 [org.xerial/sqlite-jdbc "3.41.2.1"]
                 [com.github.seancorfield/next.jdbc "1.3.955"]
                 [com.github.clj-easy/graal-build-time "1.0.5"]]

  :main sqlite.main

  :uberjar-name "sqlite.jar"

  :profiles {:uberjar {:aot :all}
             :dev     {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image"
    "-Ob"
    "-H:+TraceNativeToolUsage"
    "-H:+AllowIncompleteClasspath"
    "--verbose"
    "--no-fallback"
    "--report-unsupported-elements-at-runtime"
    "--features=clj_easy.graal_build_time.InitClojureClasses"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
