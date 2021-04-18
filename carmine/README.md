# carmine

Project to test if (carmine)[https://github.com/ptaoussanis/carmine] can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [com.taoensso/carmine "3.1.0"]

Test with:

    lein do clean, uberjar, native, run-native

Fails with:

**Note: Tested with both GraalVM Java 11 and Java 8 and fails with the same result.**
```
Exception in thread "main" java.lang.IllegalArgumentException: Unable to create org.apache.commons.pool2.impl.EvictionPolicy instance of type org.apache.commons.pool2.impl.DefaultEvictionPolicy
	at org.apache.commons.pool2.impl.BaseGenericObjectPool.setEvictionPolicyClassName(BaseGenericObjectPool.java:667)
	at org.apache.commons.pool2.impl.BaseGenericObjectPool.setEvictionPolicyClassName(BaseGenericObjectPool.java:698)
	at org.apache.commons.pool2.impl.BaseGenericObjectPool.setConfig(BaseGenericObjectPool.java:240)
	at org.apache.commons.pool2.impl.GenericKeyedObjectPool.setConfig(GenericKeyedObjectPool.java:246)
	at org.apache.commons.pool2.impl.GenericKeyedObjectPool.<init>(GenericKeyedObjectPool.java:120)
	at org.apache.commons.pool2.impl.GenericKeyedObjectPool.<init>(GenericKeyedObjectPool.java:95)
	at taoensso.carmine.connections$fn__4374.invokeStatic(connections.clj:198)
	at taoensso.carmine.connections$fn__4374.invoke(connections.clj:175)
	at clojure.lang.AFn.applyToHelper(AFn.java:154)
	at clojure.lang.AFn.applyTo(AFn.java:144)
	at clojure.core$apply.invokeStatic(core.clj:667)
	at clojure.core$apply.invoke(core.clj:662)
	at taoensso.encore$memoize_$fn__1689$fn__1694.invoke(encore.cljc:1843)
	at clojure.lang.Delay.deref(Delay.java:42)
	at clojure.core$deref.invokeStatic(core.clj:2324)
	at clojure.core$deref.invoke(core.clj:2310)
	at taoensso.encore$memoize_$fn__1689.doInvoke(encore.cljc:1828)
	at clojure.lang.RestFn.invoke(RestFn.java:408)
	at taoensso.carmine.connections$pooled_conn.invokeStatic(connections.clj:232)
	at taoensso.carmine.connections$pooled_conn.invoke(connections.clj:229)
	at simple.main$_main.invokeStatic(main.clj:9)
	at simple.main$_main.invoke(main.clj:8)
	at clojure.lang.AFn.applyToHelper(AFn.java:152)
	at clojure.lang.AFn.applyTo(AFn.java:144)
	at simple.main.main(Unknown Source)
Caused by: java.lang.ClassNotFoundException: org.apache.commons.pool2.impl.DefaultEvictionPolicy
	at com.oracle.svm.core.hub.ClassForNameSupport.forName(ClassForNameSupport.java:60)
	at java.lang.Class.forName(DynamicHub.java:1260)
	at org.apache.commons.pool2.impl.BaseGenericObjectPool.setEvictionPolicy(BaseGenericObjectPool.java:680)
	at org.apache.commons.pool2.impl.BaseGenericObjectPool.setEvictionPolicyClassName(BaseGenericObjectPool.java:658)
	... 24 more
```