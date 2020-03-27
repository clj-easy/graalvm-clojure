# monger

Testing whether [monger](https://github.com/michaelklishin/monger) can be used with MongoDB in a native binary image with GraalVM.

## Usage

Currently testing:

    [com.novemberain/monger "3.5.0"]

Test with:

    Start mongodb in the terminal using `docker-compose up`

    Then in a new terminal: `lein do clean, uberjar, native, run-native`


## Results
`[monger.core :as mg]` :x:   
`[monger.credentials :as mcr]` :x:   

## Notes
Compilation fails due to the usual culprit. SSL. 
```
Warning: Aborting stand-alone image build. Unsupported features in 2 methods
Detailed message:
Error: No instances of sun.security.ssl.SSLContextImpl$DefaultSSLContext are allowed in the image heap as this class should be initialized at image runtime. To see how this object got instantiated use -H:+TraceClassInitialization.
Trace: Object was reached by 
	reading field sun.security.ssl.SSLSocketFactoryImpl.context of
		constant sun.security.ssl.SSLSocketFactoryImpl@18e2c2e2 reached by 
	scanning method com.mongodb.MongoClientOptions.getSocketFactory(MongoClientOptions.java:703)
Call path from entry point to com.mongodb.MongoClientOptions.getSocketFactory(): 
	at com.mongodb.MongoClientOptions.getSocketFactory(MongoClientOptions.java:700)
	at com.mongodb.Mongo.createCluster(Mongo.java:757)
	at com.mongodb.Mongo.createCluster(Mongo.java:743)
	at com.mongodb.Mongo.<init>(Mongo.java:295)
	at com.mongodb.Mongo.<init>(Mongo.java:290)
	at com.mongodb.MongoClient.<init>(MongoClient.java:195)
	at monger.core$connect.invokeStatic(core.clj:91)
	at monger.core$connect.invoke(core.clj:66)
	at clojure.core$partial$fn__5843.invoke(core.clj:2637)
	at clojure.lang.AFn.run(AFn.java:22)
	at java.lang.Thread.run(Thread.java:748)
	at com.oracle.svm.core.thread.JavaThreads.threadStartRoutine(JavaThreads.java:527)
	at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:193)
	at com.oracle.svm.core.code.IsolateEnterStub.PosixJavaThreads_pthreadStartRoutine_e1f4a8c0039f8337338252cd8734f63a79b5e3df(generated:0)
Error: unbalanced monitors: mismatch at monitorexit, 3|LoadField#lockee__5436__auto__ != 96|LoadField#lockee__5436__auto__
Call path from entry point to clojure.spec.gen.alpha$dynaload$fn__2628.invoke(): 
	at clojure.spec.gen.alpha$dynaload$fn__2628.invoke(alpha.clj:21)
	at clojure.lang.AFn.run(AFn.java:22)
	at java.lang.Thread.run(Thread.java:748)
	at com.oracle.svm.core.thread.JavaThreads.threadStartRoutine(JavaThreads.java:527)
	at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:193)
	at com.oracle.svm.core.code.IsolateEnterStub.PosixJavaThreads_pthreadStartRoutine_e1f4a8c0039f8337338252cd8734f63a79b5e3df(generated:0)
Original exception that caused the problem: org.graalvm.compiler.code.SourceStackTraceBailoutException$1: unbalanced monitors: mismatch at monitorexit, 3|LoadField#lockee__5436__auto__ != 96|LoadField#lockee__5436__auto__
	at clojure.spec.gen.alpha$dynaload$fn__2628.invoke(alpha.clj:22)
Caused by: org.graalvm.compiler.core.common.PermanentBailoutException: unbalanced monitors: mismatch at monitorexit, 3|LoadField#lockee__5436__auto__ != 96|LoadField#lockee__5436__auto__
	at org.graalvm.compiler.java.BytecodeParser.bailout(BytecodeParser.java:3911)
	at org.graalvm.compiler.java.BytecodeParser.genMonitorExit(BytecodeParser.java:2818)
	at org.graalvm.compiler.java.BytecodeParser.processBytecode(BytecodeParser.java:5299)
	at org.graalvm.compiler.java.BytecodeParser.iterateBytecodesForBlock(BytecodeParser.java:3397)
	at org.graalvm.compiler.java.BytecodeParser.processBlock(BytecodeParser.java:3204)
	at org.graalvm.compiler.java.BytecodeParser.build(BytecodeParser.java:1085)
	at org.graalvm.compiler.java.BytecodeParser.buildRootMethod(BytecodeParser.java:979)
	at org.graalvm.compiler.java.GraphBuilderPhase$Instance.run(GraphBuilderPhase.java:84)
	at org.graalvm.compiler.phases.Phase.run(Phase.java:49)
	at org.graalvm.compiler.phases.BasePhase.apply(BasePhase.java:197)
	at org.graalvm.compiler.phases.Phase.apply(Phase.java:42)
	at org.graalvm.compiler.phases.Phase.apply(Phase.java:38)
	at com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.parse(MethodTypeFlowBuilder.java:221)
	at com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.apply(MethodTypeFlowBuilder.java:340)
	at com.oracle.graal.pointsto.flow.MethodTypeFlow.doParse(MethodTypeFlow.java:310)
	at com.oracle.graal.pointsto.flow.MethodTypeFlow.ensureParsed(MethodTypeFlow.java:300)
	at com.oracle.graal.pointsto.flow.MethodTypeFlow.addContext(MethodTypeFlow.java:107)
	at com.oracle.graal.pointsto.flow.SpecialInvokeTypeFlow.onObservedUpdate(InvokeTypeFlow.java:421)
	at com.oracle.graal.pointsto.flow.TypeFlow.notifyObservers(TypeFlow.java:344)
	at com.oracle.graal.pointsto.flow.TypeFlow.update(TypeFlow.java:386)
	at com.oracle.graal.pointsto.flow.SourceTypeFlowBase.update(SourceTypeFlowBase.java:121)
	at com.oracle.graal.pointsto.BigBang$2.run(BigBang.java:511)
	at com.oracle.graal.pointsto.util.CompletionExecutor.lambda$execute$0(CompletionExecutor.java:171)
	at java.util.concurrent.ForkJoinTask$RunnableExecuteAction.exec(ForkJoinTask.java:1402)
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
```