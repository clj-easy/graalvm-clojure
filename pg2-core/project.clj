(defproject pg2-core "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.12.0"]
                 [com.github.igrishaev/pg2-core "0.1.19"]
                 [com.github.clj-easy/graal-build-time "1.0.5"]]

  :main pg2-core.main

  :uberjar-name "pg2-core.jar"

  :profiles {:uberjar {:aot :all}
             :dev     {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image"
    "--no-fallback"
    "--allow-incomplete-classpath"
    "--report-unsupported-elements-at-runtime"
    "--features=clj_easy.graal_build_time.InitClojureClasses"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
