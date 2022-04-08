(ns simple.main
  (:require [io.pedestal.http :as http])
  (:gen-class))

(defn handler
  [_]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello GraalVM"})

(def routes
  #{["/" :get handler :route-name :hello]})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "server started on: http://localhost:3000/")
  (-> {:env          :dev
       ::http/type   :jetty
       ::http/port   3000
       ::http/join?  false
       ::http/routes routes}
      http/create-server
      http/start))

