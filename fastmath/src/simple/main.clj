(ns simple.main
  (:require [fastmath.core :as m]
            [fastmath.vector :as v]
            [fastmath.random :as r]
            [fastmath.fields :as f]
            [fastmath.kernel :as k]
            [fastmath.interpolation :as i]
            [fastmath.classification :as cl]
            [fastmath.clustering :as clust]
            [fastmath.regression :as regr]
            [fastmath.signal :as sig]
            [fastmath.transform :as trans]
            [fastmath.stats :as stats]
            [clojure.java.io :as io]
            [clojure.data.csv :as csv])
  (:gen-class))

(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn)
(m/use-primitive-operators)

;; fields

(def field (f/field :cpow3 1.0 (f/parametrization :cpow3)))

;; interpolation

(defn interpolator
  []
  (i/rbf (k/rbf :multiquadratic) [1 2 3 4] [0.3 0.5 -1 2]))

;; classification

(def iris-data (->> (io/resource "iris.csv")
                    (io/reader)
                    (csv/read-csv)
                    (drop 1)
                    (map (fn [v]
                           (let [[x y z w nm] (map read-string v)]
                             [[x y z w] (keyword (str nm))])))))

(def split
  (let [split-point (* 0.7 (count iris-data))
        iris-shuffled (shuffle iris-data)
        iris-v (map first iris-shuffled)
        iris-l (map second iris-shuffled)
        [dd dt] (split-at split-point iris-v)
        [ld lt] (split-at split-point iris-l)]
    {:data dd
     :labels ld
     :test-data dt
     :test-labels lt}))

(def train-data (:data split))
(def train-labels (:labels split))
(def test-data (:test-data split))
(def test-labels (:test-labels split))

(defn ada-boost
  []
  (let [cl (cl/ada-boost train-data train-labels)]
    (select-keys (cl/validate cl test-data test-labels) [:invalid :stats])))

;; clustering

(defn cluster
  []
  (dissoc (clust/dbscan
           (repeatedly 10000 (fn* []
                                  (vector (r/randval 0.1 (r/irand -10 10) (r/irand 100 150))
                                          (r/randval (r/irand -10 10) (r/irand 100 150))
                                          (r/randval (r/irand -10 10) (r/irand 100 150)))))
           10 20)
          :data :clustering :obj :predict))


;; regression

(defn regr
  []
  (let [r (regr/random-forest [[1] [2] [3] [4]] [0.3 0.5 -1 2])]
    (:stats (regr/validate r [[1] [2] [3] [4]] [0.3 0.5 -1 2]))))

;; signal

(defn signal
  []
  (let [lpf (sig/effect :vcf303 {:rate 10000})
        sgnal [-1.0 1.0 -0.5 0.5 -0.1 0.1 0 0]]
    (sig/apply-effects sgnal lpf)))

;; wavelets

(defn wavelets
  []
  (let [t (trans/transformer :fast :symlet-5)]
    (seq (trans/reverse-1d t (trans/compress (trans/forward-1d t [1 2 3 4]) 0.3)))))

;; dft

(defn dft
  []
  (let [t (trans/transformer :standard :dft)]
    (seq (trans/reverse-1d t (trans/forward-1d t [-1 8 7 6])))))

(defn -main []
  (println "Hello GraalVM.")
  (println "--------------")
  (println)
  (println "Random field function")
  (println "  f(x,y)=" (field (v/vec2 (r/grand) (r/grand))))
  (println "Interpolate")
  (println "  interpolate(x,y)=" ((interpolator) 2.5))
  (println "Classification")
  (println "  ada-boost: " (ada-boost))
  ;; (println "Clustering")
  ;; (println "  dbscan: " (cluster))
  (println "Regression")
  (println "  random-forest: " (regr))
  (println "Signal processing")
  (println "  vcf303 filter: " (signal))
  ;; (println "Wavelet compression")
  ;; (println "  symlet-5: " (wavelets))
  (println "DFT")
  (println "  dft: " (dft))
  (println "Stats")
  (println "  " (stats/stats-map (map ffirst iris-data))))
