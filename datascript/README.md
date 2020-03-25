# datascript

Testing whether [datascript](https://github.com/tonsky/datascript) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [datascript "0.18.10"]

Test with:

    lein do clean, uberjar, native, run-native

## Results
`[datascript.core :as d]` :white_check_mark:  

## Notes
- To get datascript to compile a [reflect-config](./reflect-config.json) file is necessary. Once this is available it compiles and runs. 
- Datascript does not compile at all when using clojure 1.10.
```
Warning: Aborting stand-alone image build. unbalanced monitors: mismatch at monitorexit, 3|LoadField#lockee__5436__auto__ != 96|LoadField#lockee__5436__auto__
Detailed message:
Call path from entry point to clojure.spec.gen.alpha$dynaload$fn__2628.invoke(): 
	at clojure.spec.gen.alpha$dynaload$fn__2628.invoke(alpha.clj:21)
	at clojure.lang.AFn.run(AFn.java:22)
	at java.lang.Thread.run(Thread.java:748)
	at com.oracle.svm.core.thread.JavaThreads.threadStartRoutine(JavaThreads.java:527)
	at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:193)
	at com.oracle.svm.core.code.IsolateEnterStub.PosixJavaThreads_pthreadStartRoutine_e1f4a8c0039f8337338252cd8734f63a79b5e3df(generated:0)

Warning: Use -H:+ReportExceptionStackTraces to print stacktrace of underlying exception
```