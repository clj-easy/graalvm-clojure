(ns simple.main
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.interceptor :as pedestal.interceptor])
  (:gen-class))

(def hello-word-interceptor
  (pedestal.interceptor/interceptor
    {:name  ::hello-world
     :enter (fn [context]
              (assoc-in context [:request :hello] :world))}))

(defn handler
  [{:keys [hello]}]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str "Hello GraalVM " (name hello))})

(defn echo-handler
  [{:keys [json-params]}]
  {:status 200
   :body   json-params})

(def routes
  #{["/" :get [hello-word-interceptor
               handler] :route-name :hello]
    ["/echo" :post [(body-params/body-params)
                    http/json-body
                    echo-handler] :route-name :hello-echo]})

(defn -main
  [& args]
  (println "server started on: http://localhost:3000/")
  (-> {:env          :dev
       ::http/type   :jetty
       ::http/port   3000
       ::http/join?  false
       ::http/routes routes}
      http/create-server
      http/start))

