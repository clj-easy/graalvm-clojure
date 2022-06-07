(ns buffy.core
  (:require [clojurewerkz.buffy.core :as bf])
  (:gen-class))



(defn -main
  [& args]
  (let [s   (bf/spec
              :hello      (bf/string-type 13)
              :the-answer (bf/int32-type))
        buf (bf/compose-buffer s)]
    (bf/set-field buf :hello "Hello GraalVM")
    (prn (bf/get-field buf :hello))

    (bf/set-field buf :the-answer 42)
    (prn (bf/get-field buf :the-answer))))
