(ns pg2-core.main
  (:gen-class)
  (:require [pg.core :as pg]
            [pg.pool :as pool]))

(def config
  {:host     "localhost"
   :port     5432
   :user     "postgres"
   :password "password"
   :database "postgres-db"})

(defn create-connection [config]
  (pg/connect config))

(defn create-table! [conn]
  (pg/execute conn "DROP TABLE IF EXISTS students")
  (pg/execute conn "CREATE TABLE IF NOT EXISTS students (name VARCHAR(100) NOT NULL)"))

(defn insert-student! [conn name]
  (pg/execute conn
              "INSERT INTO students (name) VALUES ($1)
              returning *"
              {:params [name]}))

(defn lookup [conn name]
  (pg/execute conn
              "SELECT * FROM students WHERE name = $1"
              {:params [name]}))

(defn using-conn []
  (println "----Using Connection----")
  (println "PG2 Connection:" (create-connection config))
  (let [conn (create-connection config)]
    (println "Table creation:" (create-table! conn))
    (println "Student 'Manuel Gomes' inserted:" (insert-student! conn "Manuel Gomes"))
    (println "Query 'Manuel Gomes's Student entity:" (lookup conn "Manuel Gomes"))
    (println "Connection closed:" (nil? (pg/close conn)))))

(defn using-conn-from-poll []
  (println "----Using Connection Poll----")
  (pool/with-pool [pool config]
    (pool/with-connection [conn pool]
      (println "Table creation:" (create-table! conn))
      (println "Student 'Manuel Gomes' inserted:" (insert-student! conn "Manuel Gomes"))
      (println "Query 'Manuel Gomes's Student entity:" (lookup conn "Manuel Gomes")))))

(defn -main []
  (println "Hello GraalVM.")
  (using-conn)
  (using-conn-from-poll))
