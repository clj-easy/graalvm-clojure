(ns simple.component-b
  (:require
   [integrant.core :as ig]))

(defmethod ig/init-key :comp/a
  [_ {:keys [foo]}]
  (str foo "bar"))
