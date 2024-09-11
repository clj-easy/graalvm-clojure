(ns simple.main
  (:require [clj-commons.byte-streams :as bs]
            [clj-commons.byte-streams.pushback-stream :as p])
  (:import (java.nio.channels ReadableByteChannel)
           (java.io InputStream))
  (:gen-class))

(defn -main []
  (let [greetings "Hello GraalVM."
        in (byte-array (range 100))
        p (p/pushback-stream 50)
        x (p/put-array p in 0 100)
        ary (byte-array 50)]
    (= 50 @(p/take p ary 0 50))
    (= (range 50) (seq ary))
    (= true @x)
    (= 50 @(p/take p ary 0 50))
    (bs/convert (p/put (bs/to-byte-buffer "a")
                       (bs/to-byte-buffer "b"))
                String)
    (-> (bs/to-byte-array greetings)
        (bs/convert (bs/stream-of String))
        (bs/convert (bs/seq-of ReadableByteChannel))
        (bs/convert InputStream)
        (bs/convert String)
        println)))
