(ns simple.main
  (:require [datascript.core :as d])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  (let [schema {:aka {:db/cardinality :db.cardinality/many}}
        conn   (d/create-conn schema)]
  
    (d/transact! conn [ { :db/id -1
                          :name  "Maksim"
                          :age   45
                          :aka   ["Max Otto von Stierlitz", "Jack Ryan"] } ])
    (println
      (d/q '[ :find  ?n ?a
              :where [?e :aka "Max Otto von Stierlitz"]
                    [?e :name ?n]
                    [?e :age  ?a] ]
          @conn))

    ;; Destructuring, function call, predicate call, query over collection
    (println
      (d/q '[ :find  ?k ?x
              :in    [[?k [?min ?max]] ...] ?range
              :where [(?range ?min ?max) [?x ...]]
                    [(even? ?x)] ]
            { :a [1 7], :b [2 4] }
            range))

    ;; Recursive rule
    (println
      (d/q '[ :find  ?u1 ?u2
              :in    $ %
              :where (follows ?u1 ?u2) ]
            [ [1 :follows 2]
              [2 :follows 3]
              [3 :follows 4] ]
          '[ [(follows ?e1 ?e2)
              [?e1 :follows ?e2]]
              [(follows ?e1 ?e2)
              [?e1 :follows ?t]
              (follows ?t ?e2)] ]))

    ;; Aggregates
    (println
      (d/q '[ :find ?color (max ?amount ?x) (min ?amount ?x)
              :in   [[?color ?x]] ?amount ]
          [[:red 10]  [:red 20] [:red 30] [:red 40] [:red 50]
            [:blue 7] [:blue 8]]
          3)))
  (println "Hello GraalVM."))

