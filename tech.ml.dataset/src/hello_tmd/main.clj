(ns hello-tmd.main
  (:require [tech.v3.dataset :as ds]
            [tech.v3.datatype.functional :as dfn]
            [tech.v3.libs.parquet :as parquet])
  (:gen-class))


(defn -main
  [& args]
  (let [test-ds (ds/->dataset "https://raw.githubusercontent.com/techascent/tech.ml.dataset/master/test/data/stocks.csv" {:key-fn keyword})]
    (println test-ds)
    (println "price mean:" (dfn/mean (test-ds :price)))
    (parquet/ds->parquet test-ds "stocks.parquet")
    (println "succesfully wrote stocks.parquet")
    (let [pds (first (parquet/parquet->ds-seq "stocks.parquet"))]
      (println "price mean:" (dfn/mean (pds "price"))))
    0))
