(ns simple.main
  (:require [cognitect.aws.client.api :as aws]
            ;; add this for graalvm (explicit load)
            ;; there are dynamically loaded at runtime
            [cognitect.aws.http.cognitect]
            [cognitect.aws.protocols.json]
            [cognitect.aws.protocols.rest]
            [cognitect.aws.protocols.rest-xml]
            [clojure.spec.alpha])
  (:gen-class))


(defn -main []
  ;; create a http-client and pass it while create aws clients to avoid dynamic loading
  ;; use a `delay` if you want to share the http-client across many aws-clients
  (let [s3 (aws/client {:api :s3 :http-client (cognitect.aws.http.cognitect/create)})]
    (prn (aws/invoke s3 {:op :ListBuckets}))))
