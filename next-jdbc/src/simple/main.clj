(ns simple.main
  (:require [next.jdbc :as jdbc]
            [clojure.string :as str])
  (:import org.apache.commons.codec.binary.Base64)
  (:gen-class))

(set! *warn-on-reflection* true)

(def sql-create-table 
  ["CREATE TABLE  IF NOT EXISTS graalvm_test (id SERIAL PRIMARY KEY, name VARCHAR(10) NOT NULL, appearance VARCHAR(10) NOT NULL, cost INT NOT NULL)"])

(def sql-read-all 
  ["SELECT * FROM graalvm_test"])

(def sql-delete-table 
  ["DELETE FROM graalvm_test"])


(def sql-insert-fruits 
  ["INSERT INTO graalvm_test (name, appearance, cost) VALUES ('Apple', 'rosy', 509), ('Pear', 'pearish', 428), ('Orange', 'round', 724)"])


(defn -main [host db user password]
  (let [datasource (jdbc/get-datasource {:dbtype "postgresql"
                                         :dbname db
                                         :user user
                                         :password password
                                         :host host
                                         :port 5432
                                         :useSSL false})
        table-name "graalvm_test"]
    (with-open [connection (jdbc/get-connection datasource)]
      (jdbc/execute! connection sql-create-table)
      (jdbc/execute! connection sql-insert-fruits)
      (doseq [result (jdbc/execute! connection sql-read-all)]
        (println result))
      (jdbc/execute! connection sql-delete-table)))
  (println "Hello GraalVM."))
