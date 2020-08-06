(ns simple.main
  (:require [cognitect.aws.client.api :as aws])
  (:gen-class))


(defn -main []
  (let [s3 (aws/client {:api :s3})]
    (prn (aws/invoke s3 {:op :ListBuckets}))))
