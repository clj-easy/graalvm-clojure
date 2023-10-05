(ns simple.main
  (:import
   [com.google.zetasketch HyperLogLogPlusPlus HyperLogLogPlusPlus$Builder])
  (:gen-class))

(set! *warn-on-reflection* true)

(defn add! [^HyperLogLogPlusPlus hll ^String item]
  (.add hll item))

(defn serialize-as-bytes ^bytes
  [^HyperLogLogPlusPlus hll]
  (.serializeToByteArray hll))

(defn deserialize-from-bytes ^HyperLogLogPlusPlus
  [^bytes data]
  (HyperLogLogPlusPlus/forProto data))

(defn make-hll
  []
  (-> (new HyperLogLogPlusPlus$Builder)
    (.normalPrecision 15)
    (.sparsePrecision 20)
    (.buildForStrings)))

(defn -main []
  (let [hll  (make-hll)
        _    (add! hll "foo")
        _    (add! hll "bar")
        _    (add! hll "baz")
        _    (prn "RESULT1:" (.result ^HyperLogLogPlusPlus hll))

        data (serialize-as-bytes hll)
        _    (prn "DATA1:" (into [] data))

        hll2 (deserialize-from-bytes data)
        _    (prn "RESULT2:" (.result ^HyperLogLogPlusPlus hll2))

        data2 (serialize-as-bytes hll2)
        _     (prn "DATA2:" (into [] data2))
        ]
    (println "Done")))
