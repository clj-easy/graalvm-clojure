(ns simple.main
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.credentials :as mcr]
            [clojure.pprint :refer [pprint]])
  (:import [com.mongodb MongoOptions ServerAddress])
  (:gen-class))

(set! *warn-on-reflection* true)

(defn clean [data]
  (dissoc data :_id))

(defn -main []
  (let [creds (slurp "./database.env")
        ^MongoOptions opts (mg/mongo-options {:ssl-enabled false})
        ^ServerAddress sa  (mg/server-address "127.0.0.1" 27017)
        u    (second (re-find #"MONGO_INITDB_ROOT_USERNAME=(.*)" creds))
        p    (char-array (second (re-find #"MONGO_INITDB_ROOT_PASSWORD=(.*)" creds)))
        admin-db "admin"
        cred (mcr/create u admin-db p)
        conn (mg/connect sa opts cred)
        db   (mg/get-db conn "graalvm-test")
        coll "documents"]
    (mc/insert db coll { :name "Clojure" :vm "GraalVM" })
    (mc/insert-and-return db coll {:name "Clojure" :vm "JVM"})
    (mc/insert-batch db coll [{ :name "ClojureScript" :vm "GraalVM"}
                                     { :name "ClojureScript" :vm "v8" }])
    (println "\nTest 1")                                     
    (-> (mc/find-maps db coll {:vm "v8"}) pprint)                                      
    (println "\nTest 2")                                     
    (-> (mc/find-maps db coll {:vm "JVM"}) pprint)
    (println "\nTest 3")                                     
    (-> (mc/find-maps db coll {:vm "GraalVM"}) pprint) 
    (println "\nTest 4")                                     
    (-> (mc/find-maps db coll { :vm "GraalVM" }) pprint)
    (println "\nTest 5")                                     
    (-> (mc/find-maps db coll { :name "Clojure" }) pprint)
    (mc/remove db coll))
  (println "Hello GraalVM."))
