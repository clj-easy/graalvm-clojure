(ns simple.main
  (:require [taoensso.carmine :as car :refer [wcar]])
  (:gen-class))

(def pool-opts {:pool {} :spec {}})
(defmacro wcar* [& body] `(wcar pool-opts ~@body))

(defn -main []
  (println (wcar* (car/ping)))
  (println (wcar* (car/set "foo" "bar")))
  (println (wcar* (car/get "foo")))
  (println "Hello GraalVM."))

