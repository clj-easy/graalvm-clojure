(ns simple.main
  (:require [safely.core :refer [safely]])
  (:gen-class))



(defn -main [& [file]]
  (println "Attempting to read file:" file)
  (safely
   (println (slurp file))
   :on-error
   :max-retry 10
   :log-stacktrace false
   :circuit-breaker :read-file))
