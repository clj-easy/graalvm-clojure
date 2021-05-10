(ns simple.main
  (:require [cljfmt.main :as fmt])

  (:gen-class))

(defn -main [& args]
  (apply fmt/-main args))
