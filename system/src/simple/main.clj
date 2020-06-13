(ns simple.main
  (:require [simple.components :refer [prod-system]]
           [system.repl :as sr])
  (:gen-class))

(defn -main []
  (sr/set-init! #'prod-system)
  (println "Hello GraalVM."))
