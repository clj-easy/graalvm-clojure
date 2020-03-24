(ns simple.main
  (:require [next.jdbc :as jdbc]
            [clojure.string :as str]
            [honeysql.core :as sql]
            [honeysql.helpers :refer [select insert-into columns values from]])
  (:gen-class))

(set! *warn-on-reflection* true)

(def sql-create-table 
  ["CREATE TABLE  IF NOT EXISTS graalvm_test (id SERIAL PRIMARY KEY, name VARCHAR(10) NOT NULL, appearance VARCHAR(10) NOT NULL, cost INT NOT NULL)"])

(def sql-read-all 
  ["SELECT * FROM graalvm_test"])

(def honey-sql-read-all 
  (sql/format {:select [:*] :from [:graalvm_test]}))

(def honey-sql-read-all-2 
  (sql/format (-> (select :*) (from :graalvm_test)))) 

(def sql-delete-table 
  ["DELETE FROM graalvm_test"])

(def sql-insert-fruits 
  ["INSERT INTO graalvm_test (name, appearance, cost) VALUES ('Apple', 'rosy', 509), ('Pear', 'pearish', 428), ('Orange', 'round', 724)"])

(def honey-sql-insert-fruits
  (-> (insert-into :graalvm_test)
      (columns :name :appearance :cost)
      (values [ ["Grape" "tiny" 1]
                ["Mango" "odd" 312]
                ["Pineapple" "spiky" 956]])
      sql/format))

(defn -main []
  (let [creds (slurp "./database.env")
        datasource (jdbc/get-datasource {:dbtype "postgresql"
                                         :dbname (second (re-find #"POSTGRES_DB=(.*)" creds))
                                         :user (second (re-find #"POSTGRES_USER=(.*)" creds))
                                         :password (second (re-find #"POSTGRES_PASSWORD=(.*)" creds))
                                         :host "localhost"
                                         :port 5432
                                         :useSSL false})
        table-name "graalvm_test"]
    (with-open [connection (jdbc/get-connection datasource)]
      (println "create table" table-name)
      (jdbc/execute! connection sql-create-table)
      (println "inserting fruit into" table-name)
      (jdbc/execute! connection sql-insert-fruits)
      (jdbc/execute! connection honey-sql-insert-fruits)
      (println "read with next.jdbc:")
      (doseq [result (jdbc/execute! connection sql-read-all)]
        (println result))
      (println "read with honey-sql:")
      (doseq [result (jdbc/execute! connection honey-sql-read-all)]
        (println result))
      (println "read with honey-sql helpers:")
      (doseq [result (jdbc/execute! connection honey-sql-read-all-2)]
        (println result))  
      (jdbc/execute! connection sql-delete-table)))
  (println "Hello GraalVM."))
