(ns simple.main
  (:require ;[fire.auth :as auth]
            [fire.core :as fire])
  (:gen-class))


(defn -main []
  (let [db "http://localhost:9000"
        ; auth (auth/create-token "GOOGLE_APPLICATION_CREDENTIALS") ; actual auth with firebase server 
        path "/graal"]
  (fire/push! db path {:hi "GraalVM"} nil)
  (fire/push! db path {:hi "Clojure"} nil)
  (println (fire/read db path nil))
  (fire/write! db path {:fire "Base"} nil)
  (println (fire/read db path nil))
  ; How you auth against the actual firebase server (see https://github.com/alekcz/fire)
  ; (fire/push! (:project-id auth) path {:autheticated "Using Google Server"} auth)
  ; (println (fire/read (:project-id auth) path auth))
  (fire/delete! db path nil)
  (println (fire/read db path nil))))
