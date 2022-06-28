(ns simple.main
  (:require [sats.upit :as upit])
  (:gen-class))


(def app (atom {}))


(def app-def
  [{:key  :configuration
    :setup (constantly {:value 1})
    :tear-down (fn [configuration] nil)}

   {:key :plus1
    :setup (fn [{:keys [configuration] :as state}]
             (partial + (:value configuration)))
    :tear-down (constantly nil)}

   {:key :plus1-times2
    :setup (fn [{:keys [plus1] :as state}]
             (comp (partial * 2) plus1))
    :tear-down (constantly nil)}])



(defn -main []
  (println "Hello GraalVM.")
  (println "\nInitializing...")
  (upit/up! app app-def)
  (prn @app)

  (println "\nRunning...")
  (println ((:plus1-times2 @app) 3))

  (println "\nShutting down...")
  (upit/down! app app-def)
  (prn @app))
