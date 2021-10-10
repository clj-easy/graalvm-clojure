(ns simple.main
  (:require
    [simple.schema :as s]
    [com.walmartlabs.lacinia :as lacinia]
    [clojure.pprint :refer [pprint]])
  (:gen-class))

(defn -main []
  (let [schema (s/load-schema)
        query "book_by_id"
        book-id "6a2b1cf-d34f-a316-d3aac3b24fca"
        q-str (format "{
                          %s(id: \"%s\") {
                              title
                              authors {
                                  first_name
                                  last_name
                              }
                              subject
                          }
                       }"
                      query
                      book-id)
        {:keys [data errors]} (lacinia/execute schema q-str nil nil)]
    (-> (if (nil? errors)
          ((keyword query) data)
          errors)
        pprint)))
