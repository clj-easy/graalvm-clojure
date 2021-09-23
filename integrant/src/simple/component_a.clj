(ns simple.component-a
  (:require
   [integrant.core :as ig]))

(defmethod ig/init-key :comp/b
  [_ _]
  "foo")
