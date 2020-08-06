# Nippy

Testing whether [Nippy](https://github.com/ptaoussanis/nippy) library can be used in a native binary image with GraalVM.

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

This is mainly due to the use of `(Class/forName class-name)` in Nippy as described [here](https://github.com/ptaoussanis/nippy/issues/129).
However after fixing this issue, the next exception I get is the following

``` text
Exception in thread "main" com.oracle.svm.core.jdk.UnsupportedFeatureError: ObjectOutputStream.writeObject()
	at com.oracle.svm.core.util.VMError.unsupportedFeature(VMError.java:86)
	at java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:68)
	at taoensso.nippy.utils$fn__2429$test_fn__2430.invoke(utils.clj:30)
	at taoensso.nippy.utils$fn__2415$memoize_type_test__2416$fn__2417$test__2418.invoke(utils.clj:18)
	at taoensso.nippy.utils$fn__2415$memoize_type_test__2416$fn__2417$fn__2420$fn__2421.invoke(utils.clj:20)
	at clojure.lang.Delay.deref(Delay.java:42)
	at clojure.core$deref.invokeStatic(core.clj:2320)
	at clojure.core$deref.invoke(core.clj:2306)
	at taoensso.nippy.utils$fn__2415$memoize_type_test__2416$fn__2417.invoke(utils.clj:19)
	at taoensso.nippy.utils$fn__2429$fn__2432.invoke(utils.clj:43)
	at taoensso.nippy$try_write_serializable.invokeStatic(nippy.clj:715)
	at taoensso.nippy$try_write_serializable.invoke(nippy.clj:714)
	at taoensso.nippy$fn__3098.invokeStatic(nippy.clj:960)
	at taoensso.nippy$fn__3098.invoke(nippy.clj:948)
	at taoensso.nippy$fn__2886$G__2881__2893.invoke(nippy.clj:332)
	at taoensso.nippy$fn__2921.invokeStatic(nippy.clj:349)
	at taoensso.nippy$fn__2921.invoke(nippy.clj:334)
	at taoensso.nippy$fn__2904$G__2899__2911.invoke(nippy.clj:333)
	at taoensso.nippy$freeze$fn__3124.invoke(nippy.clj:1026)
	at taoensso.nippy$freeze.invokeStatic(nippy.clj:1026)
	at taoensso.nippy$freeze.invoke(nippy.clj:1002)
	at taoensso.nippy$freeze.invokeStatic(nippy.clj:1005)
	at taoensso.nippy$freeze.invoke(nippy.clj:1002)
	at simple.main$_main.invokeStatic(main.clj:31)
	at simple.main$_main.invoke(main.clj:30)
	at clojure.lang.AFn.applyToHelper(AFn.java:152)
	at clojure.lang.AFn.applyTo(AFn.java:144)
	at simple.main.main(Unknown Source)
```

Which as explained [here](https://github.com/oracle/graal/issues/460)
is due to the fact that Java Serialization is not supported yet!.

So it looks like you can use Nippy with GraalVM on all Clojure values
but Java Object serialization isn't supported yet.
