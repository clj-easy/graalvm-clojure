(ns simple.main
  (:require [com.brunobonacci.sophia :as sph])
  (:gen-class))



(defn -main []

  (let [env (sph/sophia
             {;; where to store the files on disk
              :sophia.path "/tmp/sophia-test"
              ;; which logical databases to create
              :dbs ["accounts", {:name "transactions"}]})]
    (prn "SET" (sph/set-value!  env "accounts" "user1" "John"))
    (prn "GET" (sph/get-value   env "accounts" "user1"))))
