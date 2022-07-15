(ns simple.main
  (:require [clojure.tools.logging :as log])
  (:gen-class))



(defn -main []
  (log/info "Hello GraalVM")
  (log/debug "This is a debug message!")
  ;; uncomment this to test SLF4j as well
  ;;(.info (org.slf4j.LoggerFactory/getLogger String) "This is from SLF4j")
  (println "done!"))
