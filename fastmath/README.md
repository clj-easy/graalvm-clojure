# Fastmath

Use this project as template for testing a specific library with GraalVM **native-image**

## GraalVM

    openjdk 11.0.7 2020-04-14
    OpenJDK Runtime Environment GraalVM CE 20.1.0 (build 11.0.7+10-jvmci-20.1-b02)
    OpenJDK 64-Bit Server VM GraalVM CE 20.1.0 (build 11.0.7+10-jvmci-20.1-b02, mixed mode, sharing)

## Usage

Currently testing:

    [generateme/fastmath "1.5.3-SNAPSHOT"]

Test with:

    lein do clean, uberjar, native
    ./target/sample-project

Add any info might be useful for the reader.

## Problems

Most problems lays in accessing depending Java libraries.

### Optimization package

Throws an exception on Java Commons Math BOBYQA during analysis phase

```
Warning: Aborting stand-alone image build. Too many loops in method
Detailed message:
Call path from entry point to org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb(double[], double[]): 
	at org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb(BOBYQAOptimizer.java:384)
	at org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa(BOBYQAOptimizer.java:340)
	at org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.doOptimize(BOBYQAOptimizer.java:252)
	at org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.doOptimize(BOBYQAOptimizer.java:49)
	at org.apache.commons.math3.optim.BaseOptimizer.optimize(BaseOptimizer.java:153)
	at org.apache.commons.math3.optim.univariate.UnivariateOptimizer.optimize(UnivariateOptimizer.java:70)
	at org.apache.commons.math3.optim.univariate.UnivariateOptimizer.optimize(UnivariateOptimizer.java:31)
	at fastmath.optimization$optimizer$local_optimizer__4646.invoke(optimization.clj:290)
	at clojure.instant.proxy$java.lang.ThreadLocal$ff19274a.hashCode(Unknown Source)
	at java.util.HashMap.hash(HashMap.java:339)
	at java.util.HashMap.get(HashMap.java:552)
	at com.oracle.svm.jni.access.JNIReflectionDictionary.getLinkage(JNIReflectionDictionary.java:145)
	at com.oracle.svm.jni.functions.JNIFunctions.RegisterNatives(JNIFunctions.java:350)
	at com.oracle.svm.core.code.IsolateEnterStub.JNIFunctions_RegisterNatives_7728ce15b57af339792ad95c60f247990e0df65e(generated:0)
```

### Clustering

After execution DBSCAN class is not found

```
Exception in thread "main" java.lang.ClassNotFoundException: smile.clustering.DBSCAN
	at com.oracle.svm.core.hub.ClassForNameSupport.forName(ClassForNameSupport.java:60)
	at java.lang.Class.forName(DynamicHub.java:1207)
	at clojure.lang.RT.classForName(RT.java:2211)
	at clojure.lang.RT.classForName(RT.java:2220)
	at fastmath.clustering$dbscan.invokeStatic(clustering.clj:239)
	at fastmath.clustering$dbscan.invokePrim(clustering.clj)
	at fastmath.clustering$dbscan.invokeStatic(clustering.clj:238)
	at fastmath.clustering$dbscan.invoke(clustering.clj:227)
	at simple.main$cluster.invokeStatic(main.clj:66)
	at simple.main$cluster.invoke(main.clj:64)
	at simple.main$_main.invokeStatic(main.clj:100)
	at simple.main$_main.invoke(main.clj:89)
	at clojure.lang.AFn.applyToHelper(AFn.java:152)
	at clojure.lang.AFn.applyTo(AFn.java:144)
	at simple.main.main(Unknown Source)
```

### Transformation (wavelets)

```
Exception in thread "main" java.lang.IllegalArgumentException: No matching method compress found taking 1 args for class jwave.compressions.CompressorMagnitude
	at clojure.lang.Reflector.invokeMatchingMethod(Reflector.java:127)
	at clojure.lang.Reflector.invokeInstanceMethod(Reflector.java:102)
	at fastmath.transform$compress.invokeStatic(transform.clj:207)
	at fastmath.transform$compress.invokePrim(transform.clj)
	at simple.main$wavelets.invokeStatic(main.clj:97)
	at simple.main$wavelets.invoke(main.clj:94)
	at simple.main$_main.invokeStatic(main.clj:116)
	at simple.main$_main.invoke(main.clj:99)
	at clojure.lang.AFn.applyToHelper(AFn.java:152)
	at clojure.lang.AFn.applyTo(AFn.java:144)
	at simple.main.main(Unknown Source)
```

