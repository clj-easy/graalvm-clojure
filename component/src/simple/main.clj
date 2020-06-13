(ns simple.main
  (:require [com.stuartsierra.component :as comp]
            [simple.components :as sc])
  (:gen-class))


(defn -main []
  (comp/start-system (sc/prod-system))
  (println "Hello GraalVM."))
