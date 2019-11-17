(defproject simple "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :managed-dependencies
  [[io.netty/netty-codec-http "4.1.39.Final"]
   [io.netty/netty-codec "4.1.39.Final"]
   [io.netty/netty-handler-proxy "4.1.39.Final"]
   [io.netty/netty-codec-socks "4.1.39.Final"]
   [io.netty/netty-handler "4.1.39.Final"]
   [io.netty/netty-resolver-dns "4.1.39.Final"]
   [io.netty/netty-codec-dns "4.1.39.Final"]
   [io.netty/netty-resolver "4.1.39.Final"]
   [io.netty/netty-transport-native-epoll "4.1.39.Final"]
   [io.netty/netty-common "4.1.39.Final"]
   [io.netty/netty-transport-native-unix-common "4.1.39.Final"]
   [io.netty/netty-transport "4.1.39.Final"]
   [io.netty/netty-buffer "4.1.39.Final"]]


  :dependencies [[org.clojure/clojure "1.10.0"]
                 [aleph "0.4.7-alpha5"]]
  :main simple.main
  :uberjar-name "simple-main.jar"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime" "--no-server" "--no-fallback"
    "-H:+ReportExceptionStackTraces"
    "--initialize-at-build-time"
    "--initialize-at-run-time=io.netty.channel.epoll.EpollEventArray,io.netty.channel.unix.Errors,io.netty.channel.unix.IovArray,io.netty.channel.unix.Socket,io.netty.channel.epoll.Native,io.netty.channel.epoll.EpollEventLoop,io.netty.util.internal.logging.Log4JLogger"
    "--allow-incomplete-classpath"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
