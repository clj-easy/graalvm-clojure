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
  (let [s (server/run-server handler {:port 3000 :legacy-return-value? false})]
    (println "visiting http://localhost:3000/")
    
    (let [{:keys [_ _ body error]} @(client/get "http://localhost:3000/" {})]
      (if error
        (println "Failed, exception is " error)
        (println "Sync HTTP GET: " body)))
    (println "visiting https://localhost:3000/")              
    (client/get "http://localhost:3000//" {}
            (fn [{:keys [_ _ body error]}] 
              (if error
                (println "Failed, exception is " error)
                (println "Async HTTP GET: " body))))
    (future (server/server-stop! s {:timeout 1000}))
    (Thread/sleep 1500)
    (shutdown-agents)))

