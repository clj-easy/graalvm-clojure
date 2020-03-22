(ns simple.main
  (:require [org.httpkit.server :as server]
            [org.httpkit.client :as client])
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
  (server/run-server handler {:port 3000})
  (println "visiting http://localhost:3000/")
  
  ;http-kit client fails to compile
  (comment 
    (client/get "http://localhost:3000/" {}
            (fn [{:keys [status headers body error]}] 
              (if error
                (println "Failed, exception is " error)
                (println "Async HTTP GET: " body))))
    (println "visiting http://example.com/")              
    (client/get "http://example.com/" {}
            (fn [{:keys [status headers body error]}] 
              (if error
                (println "Failed, exception is " error)
                (println "Async HTTP GET: " body))))))

