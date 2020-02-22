(ns secure-random-trick.core
  (:gen-class)
  (:import (java.security SecureRandom)
           (java.util Random)))

(def ^:dynamic *prng*
  (-> (SecureRandom.)
      delay ;; comment this line out to break native-image
      )
  )

;; Caused by: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException: No instances of sun.security.provider.NativePRNG are allowed in the image heap as this class should be initialized at image runtime. To see how this object got instantiated use -H:+TraceClassInitialization.
;; Detailed message:
;; Trace:  object java.security.SecureRandom
;; object clojure.lang.Var
;; method secure_random_trick.core$next_random_long_BANG_.invokeStatic()

(defn next-random-long!
  []
  (.nextLong ^Random (force *prng*)))

(defn -main
  [& _]
  (println (next-random-long!)))
