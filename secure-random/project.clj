(defproject secure-random "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :plugins [[io.taylorwood/lein-native-image "0.3.1"]]
  :native-image {;; name of output image, optional
                 :name "simple-main"
                 ;; path to GraalVM home, optional
                 :graal-bin
                 "/Library/Java/JavaVirtualMachines/graalvm-ce-java11-20.0.0/Contents/Home/bin"

                 ;; pass-thru args to GraalVM native-image, optional
                 :opts ["--verbose"
                        "--no-fallback"
                        "--enable-all-security-services"
                        "--initialize-at-build-time"
                        "-H:+ReportExceptionStackTraces"
                        "--report-unsupported-elements-at-runtime"]}


  :main simple.main

  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}
  :repl-options {:init-ns simple.main})
