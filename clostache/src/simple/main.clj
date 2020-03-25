(ns simple.main
  (:require [clostache.parser :as clo])
  (:gen-class))

(def example-1 
  "{{greeting.text}}")

(def example-2 
  "{{{html}}}")

(def example-3
  "{{#greet}}Hello, example-3!{{/greet}}")

(def example-4
"<ul>
  {{#people}}
      <li>{{name}}</li>
  {{/people}}
</ul>")

(def example-5
  "{{#greeting}}{{text}}!{{/greeting}}")

(def example-6
  "{{^ignore}}Hello, example-6!{{/ignore}}")

(def example-7 
"<ul>
  {{#names}}
      <li>Example {{.}}</li>
  {{/names}}
</ul>")

(def example-8 
  "{{#greet}}Example 8{{/greet}}")

(def last-example
  "{{#people}}Hello {{#upper}}{{name}}{{/upper}}{{/people}}")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (clo/render example-1 {:greeting {:text "Hello, example-1"}}))
  (println (clo/render example-2 {:html "<h1>Hello, example-2!</h1>"}))
  (println (clo/render example-3 {:greet true}))
  (println (clo/render example-4 {:people [{:name "Example 4"} {:name "GraalVM"} {:name "JVM"}]}))
  (println (clo/render example-5 {:greeting {:text "Hello, example-5"}}))
  (println (clo/render example-6 {:ignore false}))
  (println (clo/render example-7 {:names ["Seven" "Siete" "Sebun" "Abiriyo"]}))
  (println (clo/render example-8 {:greet #(str "Hello, " %)}))
  (println (clo/render last-example {:people [{:name "graalvm"}] 
                                                    :upper (fn [text] 
                                                              (fn [render-fn] 
                                                                (clojure.string/upper-case (render-fn text))))})))

