(ns simple.main
  (:require [selmer.parser :refer [render]]
            [selmer.filters :refer [add-filter!]]
            [clojure.string :as str])
  (:gen-class))

(defn -main []
  (add-filter! :embiginate str/upper-case)
  (println (render "Hello {{name}}!" {:name "GraalVM"}))
  (println (render "{{d|date:\"yyyy-MM-dd\"}}" {:d (java.util.Date.)}))
  (println (render "{{number|phone}}" {:number "01234 567890"}))
  (println (render "{{items|count}} item{{items|pluralize}}" {:items [1]}))
  (println (render "{{foo|rand-nth}}" {:foo [1 2 3]}))
  (println (render "{{data}}" {:data "<foo>"}))
  (println (render "{{data|safe}}" {:data "<foo>"}))
  (println (render "{{name|default-if-empty:\"I <3 ponies\"}}" {:name "GraalVM"}))
  (println (render "{% if xs|empty? %}foo{% endif %}" {:xs []}))
  (println (render "{{domain|hash:\"md5\"}}" {:domain "example.org"}))
  (println (render "{{shout|embiginate}}" {:shout "graalvm"})))
