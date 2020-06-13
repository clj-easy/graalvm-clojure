(ns spec-graal.main
  (:gen-class)
  (:require [clojure.spec.alpha :as s]))

(s/def ::title string?)

(defn -main []
  (let [title "Spec Graal"]
    (printf "Hello GraalVM with Spec! Btw, '%s' %s a valid ::title.\n"
            title (if (s/valid? ::title title) "is" "is NOT")))
  (flush))
