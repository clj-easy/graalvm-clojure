(ns simple.main
  (:require [com.brunobonacci.mulog :as u])
  (:gen-class))

;;
;; Update this file to use the libary you wish to test.
;;


(defn -main []
  (u/log ::hello :to "GraalVM")
  (let [p (u/start-publisher! {:type :console :pretty? true})]
    (Thread/sleep 1000)
    ;; stopping publisher
    (p))
  (println "DONE"))
