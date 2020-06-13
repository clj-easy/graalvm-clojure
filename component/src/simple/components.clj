(ns simple.components
  (:require [com.stuartsierra.component :as comp]))

(defrecord Logging []
  comp/Lifecycle
  (start [component]
    (println "started logging component")
    (assoc component :logging true))
  (stop [component] component
    (assoc component :logging false)))


(defn new-logging []
  (->Logging))

(defn prod-system []
  (comp/system-map
    :logging (new-logging)))
