(ns simple.main
  (:require [pp-grid.examples :as e])
  (:gen-class))

(defn -main []
  (do
    (println (e/make-grid-illustration 8 8))
    (println (e/make-hello-world))
    (println (e/make-abcd))
    (println (e/make-boxed-abcd))
    (println (e/make-haligned-boxes))
    (println (e/make-tables))
    (println (e/make-nested-table))
    (println (e/make-decorated-text "Hello GraalVM"))
    (println (e/make-colored-table))
    (println (e/make-colored-boxes))
    (println (e/make-tree))
    (println (e/make-chart-xy))
    (println (e/make-chart-bar))
    (println (e/make-chart-bar-vertical))
    (println (e/make-transformations))
    (println (e/make-diagram))
    (println (e/make-paragraphs))))
