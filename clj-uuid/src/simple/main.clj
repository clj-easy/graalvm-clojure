(ns simple.main
  (:require [clj-uuid :as uuid])
  (:gen-class))

;;
;; Update this file to use the libary you wish to test.
;;


(defn -main []
  (println (uuid/null))
  (println (uuid/v0))
  (println uuid/+null+)
  (println (uuid/v1))
  (println (uuid/get-timestamp (uuid/v1)))
  (println (uuid/get-instant (uuid/v1)))
  (println (uuid/v3 uuid/+namespace-dns+ "www.clojure.org"))
  (println (uuid/v5 uuid/+namespace-oid+ "0.1.22.13.8.236.1"))
  (println (uuid/v5 uuid/+namespace-url+ "I am clearly not a URL"))
  
  ; These fail because graal doesn't support Java serialization
  ; (println (uuid/v3 uuid/+namespace-url+ :keyword))
  ; (println (uuid/v3 uuid/+null+ 'this-symbol))
  ; (println (uuid/v5 uuid/+namespace-url+ :keyword))
  ; (println (uuid/v5 uuid/+null+ 'this-symbol))
  
  (-> (uuid/v1)
      (uuid/v5 "one")
      (uuid/v5 "two")
      (uuid/v5 "three")
      println)
  (println "Hello GraalVM."))
