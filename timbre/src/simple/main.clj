(ns simple.main
  (:require
    [taoensso.timbre :as timbre
      :refer [log  trace  debug  info  warn  error  fatal  report
              logf tracef debugf infof warnf errorf fatalf reportf
              spy get-env]]
    [taoensso.timbre.appenders.core :as appenders])
  (:gen-class))

(defn my-mult [x y] 
  (info "Lexical env:" (get-env)) (* x y))

(defn -main []

  (timbre/merge-config!
    {:appenders {:spit (appenders/spit-appender {:fname "./target/my-file.log"})}})

  (info "Hello GraalVM.")
  (spy :info (* 5 4 3 2 1)) 
  (my-mult 4 7)
  (trace "This won't print due to insufficient log level")
  (log :trace)
  (trace "You didn't expect this")
  (info (Exception. "Don't be alarmed by this error. We expect it.") "arg1" "arg2")
  (info "Bye GraalVM."))
