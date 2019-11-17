(ns simple.main
  (:require [aleph.http.server :as aleph])
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
  (aleph/start-server handler {:port 3000}))
