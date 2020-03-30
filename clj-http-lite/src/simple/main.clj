(ns simple.main
  (:require [clj-http.lite.client :as client])
  (:gen-class))

(def payload "{
  \"id\": 101,
  \"title\": \"GraalVM\",
  \"body\": \"Looks like clj-http.lite works on GraalVM\",
  \"userId\": 1
    }")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [get "http://localhost:3000/get" post "http://localhost:3000/post"]
  (println "GET" get "=> Response: \n" (:body (client/get get)))
  (println "POST" post "=> Response: \n" 
    (:body (client/post post {:headers {"content-type" "application/json; charset=utf-8"}
                                               :body payload})))))
          