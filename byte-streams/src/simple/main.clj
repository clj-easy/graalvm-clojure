(ns simple.main
  (:require [byte-streams :as byte-streams])
  (:gen-class))

;;
;; Update this file to use the libary you wish to test.
;;


(defn -main []
  (let [greetings "Hello GraalVM."]
    (-> (byte-streams/to-byte-array greetings)
        (byte-streams/convert String)
        println)))
