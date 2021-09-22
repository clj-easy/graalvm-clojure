(ns simple.main
  (:require [taoensso.nippy :as nippy])
  (:gen-class))

(defn -main []
  (prn (into [] (nippy/freeze nippy/stress-data)))
  (prn (nippy/thaw (nippy/freeze nippy/stress-data))))
