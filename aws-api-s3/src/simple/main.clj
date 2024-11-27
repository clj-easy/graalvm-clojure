(ns simple.main
  (:require [clojure.edn :as edn]
            [cognitect.aws.client.api :as aws]
            [cognitect.aws.credentials :as credentials]
            ;; add this for graalvm (explicit load)
            ;; there are dynamically loaded at runtime
            [cognitect.aws.http.cognitect]
            [cognitect.aws.protocols.json]
            [cognitect.aws.protocols.rest]
            [cognitect.aws.protocols.rest-xml]
            [clojure.spec.alpha])
  (:gen-class))

(defn fetch-config []
  (edn/read-string (slurp "resources/config.edn")))


(defn -main []
  ;; create a http-client and pass it while create aws clients to avoid dynamic loading
  ;; use a `delay` if you want to share the http-client across many aws-clients
  (let [config (fetch-config)
        s3 (aws/client {:api                  :s3
                        :http-client          (cognitect.aws.http.cognitect/create)
                        :credentials-provider (credentials/basic-credentials-provider config)})]
    (prn (aws/invoke s3 {:op :ListBuckets}))))
