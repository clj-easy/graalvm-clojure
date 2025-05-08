(ns simple.main
  (:require [clj-jgit.porcelain :refer [git-clone]])
  (:gen-class))


(defn -main []
  (git-clone "https://github.com/clj-jgit/clj-jgit.git" :dir "local-folder/clj-jgit")
  (println "cloned" "local-folder/clj-jgit"))
