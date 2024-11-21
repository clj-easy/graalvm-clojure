(ns sqlite.main
  (:gen-class)
  (:require [clojure.java.io :as io]
            [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]))

(defn make-spec []
  (let [lsp-db (io/file ".my-test.db")]
    {:dbtype "sqlite"
     :dbname (.getAbsolutePath lsp-db)}))

(defn insert []
  (println "Inserting...")
  (let [db-spec (make-spec)]
    (io/make-parents (:dbname db-spec))
    (with-open [conn (jdbc/get-connection db-spec)]
      (jdbc/execute! conn ["drop table if exists project;"])
      (jdbc/execute! conn ["create table project (version text, root text unique, hash text, classpath text, analysis text);"])
      (jdbc/execute! conn ["insert or replace into project
                            (version, root, hash, classpath, analysis)
                            values (?,?,?,?,?);" "1" "some-project" "some-hash" "some-classpath" "a lot of analysis"]))))

(defn select []
  (println "Selecting...")
  (try
    (with-open [conn (jdbc/get-connection (make-spec))]
      (let [project-row (-> (jdbc/execute! conn
                                           ["select root, hash, classpath, analysis from project where version = ?"
                                            "1"]
                                           {:builder-fn rs/as-unqualified-lower-maps})
                            (nth 0))]
        (println "Success: " project-row)))
    (catch Throwable e
      (println "Could not load db" (.getMessage e)))))


(defn -main []
  (println "Hello GraalVM.")
  (insert)
  (select))
