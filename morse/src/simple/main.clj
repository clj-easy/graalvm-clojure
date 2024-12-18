(ns simple.main
  (:require [morse.handlers :as h]
            [morse.api :as t]
            [morse.polling :as p]
            [clojure.core.async :refer [<!!]])
  (:gen-class))

(def token "7396862872:AAFAk8py-CbQJIY24na1Ar3cs-5NGKKyiAE")

; This will define bot-api function, which later could be
; used to start your bot
(h/defhandler bot-api

  (h/command-fn "start" (fn [{{id :id :as chat} :chat}]
                          (println "Bot joined new chat: " chat)
                          (t/send-text token id "Welcome!")))

  ; You can use short syntax for same purposes
  ; Destructuring works same way as in function above
  (h/command "help" {{id :id :as chat} :chat}
             (println "Help was requested in " chat)
             (t/send-text token id "Help is on the way"))

  ; So match-all catch-through case would look something like this:
  (h/message message (println "Intercepted message:" message)))

(defn -main [& args]
  (println "Starting the mybot")
  (<!! (p/start token bot-api)))
