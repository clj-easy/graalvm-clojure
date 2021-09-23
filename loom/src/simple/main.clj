(ns simple.main
  (:require [loom.graph :as g]
            [loom.alg :as ga])
  (:gen-class))



(defn -main []
  (let [graph (g/digraph {:a #{:b :c}, :b #{:c :e}, :c #{:e :f}, :d nil, :e #{:f}, :f nil})]
    (prn "  top-sort:" (ga/topsort graph))
    (prn "path [a-e]:" (ga/shortest-path graph :a :e))
    (println "All done.")))
