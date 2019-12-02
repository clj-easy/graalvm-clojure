(defproject secure-random-trick "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :plugins [[io.taylorwood/lein-native-image "0.3.1"]]
  :native-image {;; name of output image, optional
                 :name "srt"
                 ;; path to GraalVM home, optional
                 :graal-bin
                 ;"/Library/Java/JavaVirtualMachines/graalvm-ce-java11-19.3.0/Contents/Home/bin"
                 "/home/dimitris/graalvm-ce-java11-19.3.0/bin"

                 ;; pass-thru args to GraalVM native-image, optional
                 :opts ["--verbose"
                        "--no-fallback"
                        "--enable-all-security-services"
                        "--initialize-at-build-time"
                        ;"--initialize-at-run-time='secure_random_trick.core$next_random_long_BANG_',clojure.lang.Var"
                        ;"--rerun-class-initialization-at-runtime='sun.security.jca.JCAUtil$CachedSecureRandomHolder',java.security.SecureRandom"
                        "-H:+ReportExceptionStackTraces"
                        "--report-unsupported-elements-at-runtime"]}



  :profiles {:uberjar {:aot :all
                       :main secure-random-trick.core
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}
  :repl-options {:init-ns secure-random-trick.core})
