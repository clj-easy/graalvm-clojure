(ns simple.main
  (:require [ring.adapter.jetty :as http])
  (:gen-class))


(defn handler
  [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello GraalVM"})


(defn -main []
  (println "server started on: http://localhost:3000/")
  (http/run-jetty handler {:port 3000}))
