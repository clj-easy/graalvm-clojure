# Nippy

Testing whether Nippy library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [com.taoensso/nippy "2.14.0"]

Test with:

    lein do clean, uberjar, native, run-native


It works only with some types, for example:

      ;; Throws exception
      (defn -main []
        (prn (nippy/freeze nippy/stress-data))
        (prn (nippy/thaw (nippy/freeze nippy/stress-data))))


throws the following exception:

    Exception in thread "main" clojure.lang.ExceptionInfo: Unfreezable type: class clojure.lang.ExceptionInfo {:type clojure.lang.ExceptionInfo, :as-str "#error {\n :cause \"ExInfo\"\n :data {:data \"data\"}\n :via\n [{:type clojure.lang.ExceptionInfo\n   :message \"ExInfo\"\n   :data {:data \"data\"}}]\n :trace\n []}"}
        at taoensso.nippy$throw_unfreezable.invokeStatic(nippy.clj:720)
        at taoensso.nippy$throw_unfreezable.invoke(nippy.clj:718)
        at taoensso.nippy$fn__2923.invokeStatic(nippy.clj:924)
        at taoensso.nippy$fn__2923.invoke(nippy.clj:905)
        at taoensso.nippy$fn__2726$G__2721__2733.invoke(nippy.clj:314)
        at taoensso.nippy$fn__2761.invokeStatic(nippy.clj:331)
        at taoensso.nippy$fn__2761.invoke(nippy.clj:316)
        at taoensso.nippy$fn__2744$G__2739__2751.invoke(nippy.clj:315)
        at taoensso.nippy$write_map$fn__2819$fn__2820.invoke(nippy.clj:629)
        at taoensso.nippy$write_map$fn__2819.invoke(nippy.clj:626)
        at clojure.lang.PersistentHashMap$NodeSeq.kvreduce(PersistentHashMap.java:1307)
        at clojure.lang.PersistentHashMap$BitmapIndexedNode.kvreduce(PersistentHashMap.java:802)
        at clojure.lang.PersistentHashMap$ArrayNode.kvreduce(PersistentHashMap.java:466)
        at clojure.lang.PersistentHashMap.kvreduce(PersistentHashMap.java:236)
        at clojure.core$fn__8422.invokeStatic(core.clj:6845)
        at clojure.core$fn__8422.invoke(core.clj:6830)
        at clojure.core.protocols$fn__8152$G__8147__8161.invoke(protocols.clj:175)
        at clojure.core$reduce_kv.invokeStatic(core.clj:6856)
        at clojure.core$reduce_kv.invoke(core.clj:6847)
        at taoensso.nippy$write_map.invokeStatic(nippy.clj:626)
        at taoensso.nippy$write_map.invoke(nippy.clj:608)
        at taoensso.nippy$fn__2913.invokeStatic(nippy.clj:884)
        at taoensso.nippy$fn__2913.invoke(nippy.clj:884)
        at taoensso.nippy$fn__2726$G__2721__2733.invoke(nippy.clj:314)
        at taoensso.nippy$fn__2757.invokeStatic(nippy.clj:323)
        at taoensso.nippy$fn__2757.invoke(nippy.clj:316)
        at taoensso.nippy$fn__2744$G__2739__2751.invoke(nippy.clj:315)
        at taoensso.nippy$freeze$fn__2949.invoke(nippy.clj:982)
        at taoensso.nippy$freeze.invokeStatic(nippy.clj:982)
        at taoensso.nippy$freeze.invoke(nippy.clj:958)
        at taoensso.nippy$freeze.invokeStatic(nippy.clj:961)
        at taoensso.nippy$freeze.invoke(nippy.clj:958)
        at simple.main$_main.invokeStatic(main.clj:8)
        at simple.main$_main.invoke(main.clj:7)
        at clojure.lang.AFn.applyToHelper(AFn.java:152)
        at clojure.lang.AFn.applyTo(AFn.java:144)
        at simple.main.main(Unknown Source)
