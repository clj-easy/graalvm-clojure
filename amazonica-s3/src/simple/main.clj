(ns simple.main
  (:require [amazonica.aws.s3 :as s3])
  (:gen-class))



(defn -main []
  (prn (s3/list-buckets {})))
