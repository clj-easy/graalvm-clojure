(ns simple.main
  (:require [cognitect.aws.client.api :as aws])
  (:gen-class))


(def s3 (aws/client {:api :s3}))

(defn -main []
  (prn (aws/invoke s3 {:op :ListBuckets})))
