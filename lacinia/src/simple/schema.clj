(ns simple.schema
  "Adapted from this tutorial:
  https://lacinia.readthedocs.io/en/latest/tutorial/designer-data.html#id4"
  (:require
    [clojure.java.io :as io]
    [com.walmartlabs.lacinia.util :as util]
    [com.walmartlabs.lacinia.schema :as schema]
    [clojure.edn :as edn])
  (:gen-class))

(defn- ->entity
  [data entity-name]
  (->> (get data entity-name)
       (reduce #(assoc %1 (:id %2) %2) {})))

(defn- resolve-book-authors
  [authors-map context args book]
  (->> (:authors book) (map authors-map)))

(defn- resolve-book-subject
  [_ context args book]
  (:subject book))

(defn- resolve-book-by-id
  [books context args value]
  (let [{:keys [id]} args]
    (get books id)))

(defn- resolver-map []
  (let [data (-> (io/resource "data.edn")
                 slurp
                 edn/read-string)
        books-map (->entity data :books)
        authors-map (->entity data :authors)]
    {:Book/authors (partial resolve-book-authors authors-map)
     :Book/subject (partial resolve-book-subject nil)
     :query/book-by-id (partial resolve-book-by-id books-map)}))

(defn load-schema []
  (-> (io/resource "schema.edn")
      slurp
      edn/read-string
      (util/attach-resolvers (resolver-map))
      schema/compile))
