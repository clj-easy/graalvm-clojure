(ns simple.main
  (:require [clara.rules :refer :all]
            [simple.rules :as r])
  (:gen-class))

;;
;; Update this file to use the libary you wish to test.
;;

(defsession compiled-session
  'simple.rules)

(defn -main []
  (-> compiled-session
      (insert (r/->ClientRepresentative "Alice" "Acme")
              (r/->SupportRequest "Acme" :high))
      (fire-rules)))
