(ns simple.main
  (:require [clojure.tools.logging :as log])
  (:gen-class))



(defn -main []
  (log/info "Hello GraalVM")
  (println "done!"))
