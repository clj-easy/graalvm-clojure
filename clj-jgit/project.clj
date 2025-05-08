(defproject clj-jgit "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.12.0"]
                 [clj-jgit "1.1.0"]
                 [borkdude/graal.locking "0.0.2"]
                 [cheshire "6.0.0"]]

  :main simple.main

  :uberjar-name "simple-main.jar"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime" "--no-server"
    ; "--initialize-at-run-time=java.util.Random"
    "--initialize-at-run-time=java.util.Random"
    "--initialize-at-run-time=org.eclipse.jgit.util.FileUtils"
    "--initialize-at-run-time=org.eclipse.jgit.transport.HttpAuthMethod\\$Digest"
    "--initialize-at-run-time=org.eclipse.jgit.lib.internal.WorkQueue"
    "--initialize-at-run-time=org.eclipse.jgit.internal.storage.file.WindowCache"

    ;; java.util.jar.JarException: The JCE Provider simple-main.jar is not signed
    "--initialize-at-run-time=org.apache.sshd.common.config.keys.loader.AESPrivateKeyObfuscator\\$LazyKeyLengthsHolder"

    "--initialize-at-run-time=org.bouncycastle.jcajce.provider.drbg.DRBG\\$Default"
    
    "--initialize-at-run-time=org.bouncycastle.jcajce.provider.drbg.DRBG\\$NonceAndIV"
    
    ;; Configure simple/reflection.clj and run `lein run -m simple.reflection`
    "-H:ReflectionConfigurationFiles=resources/reflect-config.json"

    "--initialize-at-build-time"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
