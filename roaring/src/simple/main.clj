(ns simple.main
  (:import
   [java.nio ByteBuffer]
   [org.roaringbitmap RoaringBitmap]
   [org.roaringbitmap.longlong Roaring64Bitmap])
  (:gen-class))

(set! *warn-on-reflection* true)

(defn test-32bits-bitmaps
  []
  (println "\nTest 32bits version")
  (let [a  (RoaringBitmap/bitmapOf (int-array (range 100 300)))
        b  (RoaringBitmap/bitmapOf (int-array (range 200 400)))
        ab (RoaringBitmap/and a b)

        _ (println "32bits: cardinality(A ^ B):" (.getCardinality ab))
        _ (assert (= 100 (.getCardinality ab)))

        size (.serializedSizeInBytes ab)
        buf  (ByteBuffer/allocate size)

        _ (println "serialize... (size " size ")")
        _ (.serialize ab ^ByteBuffer buf)

        _ (println "deserialize... (size " size ")")
        ab' (RoaringBitmap.)
        _ (.flip buf)
        _ (.deserialize ab' ^ByteBuffer buf)
        _ (println "32bits: cardinality(ab'):" (.getCardinality ab'))
        _ (assert (= 100 (.getCardinality ab')))

        _ (.xor ab' ab)
        _ (println "same after deserialization? " (= 0 (.getCardinality ab')))
        _ (assert (= 0 (.getCardinality ab')))]))


(defn test-64bits-bitmaps
  []
  (println "\nTest 64bits version")
  (let [ab (Roaring64Bitmap/bitmapOf (long-array (range 100 300)))
        b  (Roaring64Bitmap/bitmapOf (long-array (range 200 400)))
        _  (.and ab b)

        _ (println "64bits: cardinality(A ^ B):" (.getLongCardinality ab))
        _ (assert (= 100 (.getLongCardinality ab)))

        size (.serializedSizeInBytes ab)
        buf  (ByteBuffer/allocate size)

        _ (println "serialize... (size " size ")")
        _ (.serialize ab ^ByteBuffer buf)

        _ (println "deserialize... (size " size ")")
        ab' (Roaring64Bitmap.)
        _ (.flip buf)
        _ (.deserialize ab' ^ByteBuffer buf)
        _ (println "64bits: cardinality(ab'):" (.getLongCardinality ab'))
        _ (assert (= 100 (.getLongCardinality ab')))

        _ (.xor ab' ab)
        _ (println "same after deserialization? " (= 0 (.getLongCardinality ab')))
        _ (assert (= 0 (.getLongCardinality ab')))]))


(defn -main []
  (println "Starting RoaringBitmap tests")
  (test-32bits-bitmaps)
  (test-64bits-bitmaps))


;; (-main)
