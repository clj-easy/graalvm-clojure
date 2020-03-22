(ns simple.main
  (:gen-class))

(use 'hiccup.core)

(defn -main []
  (println
    (html [:html 
         [:head
          [:script {:src "/graalvm.js"}]]
         [:body
          [:div#app.main
            [:h1.title "Testing hiccup on GraalVM"]
            [:p {:style "color:blue"} "Everything seems to be in order"]]]])))
