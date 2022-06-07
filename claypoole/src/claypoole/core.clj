(ns claypoole.core
  (:require [com.climate.claypoole :as cp])
  (:gen-class))



(defn -main
  [& args]
  (cp/with-shutdown! [pool (cp/threadpool 2)]
    (let [data       (take 10 (iterate inc 0))
          fut*       (cp/future pool constantly "fut")
          cfut*      (cp/completable-future pool constantly "cfut")
          cp-pmap    (doall (cp/pmap pool inc data))
          cp-pcalls  (doall (cp/pcalls pool (constantly 1) (constantly 2) (constantly 3)))
          cp-pvalues (doall (cp/pvalues pool (str "si" "mul") (str "ta" "neous")))
          cp-upmap   (doall (cp/upmap pool inc data))]
      (prn @fut*)
      (prn @cfut*)
      (prn cp-pmap)
      (prn cp-pcalls)
      (prn cp-pvalues)
      (prn cp-upmap))
    (shutdown-agents)))
