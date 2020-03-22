(ns simple.main
  (:require [org.httpkit.server :as http])
  (:gen-class))

(defn handler
  [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello GraalVM"})


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "server started on: http://localhost:3000/")
  (http/run-server handler {:port 3000}))
