(defproject graalvm-clojure "0.1.0-SNAPSHOT"

  :dependencies []

  :main simple.main

  :profiles {:uberjar {:aot :all}
             :clojure-1.7.0  {:dependencies [[org.clojure/clojure "1.7.0"]]
                      :uberjar-name "simple-clojure-1.7.0-uberjar.jar"}
             :clojure-1.8.0  {:dependencies [[org.clojure/clojure "1.8.0"]]
                      :uberjar-name "simple-clojure-1.8.0-uberjar.jar"}
             :clojure-1.9.0  {:dependencies [[org.clojure/clojure "1.9.0"]]
                      :uberjar-name "simple-clojure-1.9.0-uberjar.jar"}
             :clojure-1.10.0 {:dependencies [[org.clojure/clojure "1.10.0"]]
                      :uberjar-name "simple-clojure-1.10.0-uberjar.jar"}}
)
