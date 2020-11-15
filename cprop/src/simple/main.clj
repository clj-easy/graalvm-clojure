(ns simple.main
  (:require [cprop.core :refer [load-config]])
  (:gen-class))

(defn -main [& args]
  (let [config (load-config :file "test-config.edn")]
    (println config)
    (println (= 42 (config :answer))) ;; 42
    (println (= "/z-broker" (get-in config [:source :account :rabbit :vhost])))))
