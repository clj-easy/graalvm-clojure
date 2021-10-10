(defproject lacinia "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.10.3"]
                 [com.walmartlabs/lacinia "1.0"]]

  :main simple.main

  :uberjar-name "simple-main.jar"

  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime" "--no-server" "--no-fallback"
    "--initialize-at-build-time"
    "--initialize-at-run-time=java.awt,javax.accessibility,javax.swing,sun.awt,sun.font,sun.java2d,sun.swing"
    "--trace-class-initialization=java.awt.Toolkit,java.awt.Font,java.awt.RenderingHints,sun.awt.AWTAccessor,sun.awt.SunHints,sun.awt.UNIXToolkit,sun.awt.AppContext,sun.awt.OSInfo,sun.awt.X11.XToolkit,sun.awt.SunToolkit,sun.awt.X11GraphicsEnvironment"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-Djava.awt.headless=true"
    "-H:ResourceConfigurationFiles=clinit.d/resource-config.json"
    "-H:+ReportExceptionStackTraces"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
