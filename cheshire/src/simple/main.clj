(ns simple.main
  (:require [cheshire.core :as json])
  (:gen-class))



(defn -main []
  (prn (json/generate-string {:foo "bar" :baz 5}))
  (prn (json/parse-string "{\"foo\":\"bar\"}")))
