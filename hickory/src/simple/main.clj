(ns simple.main
  (:require
   [hickory.core :as h]
   [hickory.select :as s])
  (:gen-class))

(defn -main []
  (let [sample-html (str "<!DOCTYPE html>" "<html>" "<head>" "</head>" "<body>"
                         "<h1>" "Don't select me!" "</h1>"
                         "<i>" "Don't select me either!" "</i>"
                         "<p>" "Select me!" "</p>"
                         "<p>" "Select me too!" "</p>"
                         "</body>" "</html>")]
    (->> sample-html
         (h/parse)
         (h/as-hickory)
         (s/select (s/tag "p"))
         (first)
         (:content)
         (println))))
