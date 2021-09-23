(ns simple.core
  (:gen-class)
  (:require
   [integrant.core :as ig]
   [simple.component-a]
   [simple.component-b]))

(def config
  {:comp/a {:foo (ig/ref :comp/b)}
   :comp/b {}})

(def system
  (ig/init config))

(defn -main
  [& args]
  (println system))
