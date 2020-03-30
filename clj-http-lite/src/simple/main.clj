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
  (println "GET http://example.com/ => Status: " (:status (client/get "http://example.com/")))
  (println "POST http://example.com/ => Response: \n" 
    (:body (client/post "https://jsonplaceholder.typicode.com/posts" {:headers {"content-type" "application/json; charset=utf-8"}
                                                                      :body payload}))))
          