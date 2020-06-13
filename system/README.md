# Sample-project

Use this project as template for testing a specific library with GraalVM **native-image**

## Usage

Currently testing:

    [org.danielsz/system "0.4.4"]

Test with:

    lein do clean, uberjar, native, run-native

Fails with:

```
[simple_main:9808]    classlist:   2,774.75 ms,  0.96 GB
[simple_main:9808]        (cap):     845.44 ms,  0.96 GB
[simple_main:9808]        setup:   2,327.79 ms,  0.96 GB
[simple_main:9808]     (clinit):     434.24 ms,  3.46 GB
[simple_main:9808]   (typeflow):  17,666.69 ms,  3.46 GB
[simple_main:9808]    (objects):  13,939.00 ms,  3.46 GB
[simple_main:9808]   (features):     720.17 ms,  3.46 GB
[simple_main:9808]     analysis:  33,814.40 ms,  3.46 GB
Warning: Aborting stand-alone image build. Unsupported features in 2 methods
Detailed message:
Error: Frame states being merged are incompatible: unbalanced monitors - locked objects do not match
 This frame state: [locals: [1,2,41,_,_,_,_] stack: [44] locks: [] rethrowException]
Other frame state: [locals: [1,2,41,_,_,_,_] stack: [74] locks: [51 / 42] rethrowException]
Parser context: clojure.core.server$io_prepl$fn__8955.invoke(server.clj:287) [bci: 84, intrinsic: false]
  84: dup_x2
  85: if_acmpeq     92
  88: pop
  89: goto          114
  92: swap
  93: pop
  94: dup

Call path from entry point to clojure.core.server$io_prepl$fn__8955.invoke(Object):
        at clojure.core.server$io_prepl$fn__8955.invoke(server.clj:284)
        at clojure.instant.proxy$java.lang.ThreadLocal$ff19274a.hashCode(Unknown Source)
        at java.util.HashMap.hash(HashMap.java:339)
        at java.util.HashMap.get(HashMap.java:552)
        at com.oracle.svm.jni.access.JNIReflectionDictionary.getClassObjectByName(JNIReflectionDictionary.java:128)
        at com.oracle.svm.jni.functions.JNIFunctions.FindClass(JNIFunctions.java:324)
        at com.oracle.svm.core.code.IsolateEnterStub.JNIFunctions_FindClass_3ec1032c6cb9443725d1e68194130533bfb04076(generated:0)
Original exception that caused the problem: org.graalvm.compiler.core.common.PermanentBailoutException: Frame states being merged are incompatible: unbalanced monitors - locked objects do not match
 This frame state: [locals: [1,2,41,_,_,_,_] stack: [44] locks: [] rethrowException]
Other frame state: [locals: [1,2,41,_,_,_,_] stack: [74] locks: [51 / 42] rethrowException]
Parser context: clojure.core.server$io_prepl$fn__8955.invoke(server.clj:287) [bci: 84, intrinsic: false]
  84: dup_x2
  85: if_acmpeq     92
  88: pop
  89: goto          114
  92: swap
  93: pop
  94: dup

        at jdk.internal.vm.compiler/org.graalvm.compiler.java.FrameStateBuilder.checkCompatibleWith(FrameStateBuilder.java:420)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.FrameStateBuilder.merge(FrameStateBuilder.java:433)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.createTarget(BytecodeParser.java:3172)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.appendGoto(BytecodeParser.java:3345)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.createExceptionDispatch(BytecodeParser.java:3295)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.processBlock(BytecodeParser.java:3228)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.build(BytecodeParser.java:1088)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.buildRootMethod(BytecodeParser.java:982)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.GraphBuilderPhase$Instance.run(GraphBuilderPhase.java:84)
        at jdk.internal.vm.compiler/org.graalvm.compiler.phases.Phase.run(Phase.java:49)
        at jdk.internal.vm.compiler/org.graalvm.compiler.phases.BasePhase.apply(BasePhase.java:214)
        at jdk.internal.vm.compiler/org.graalvm.compiler.phases.Phase.apply(Phase.java:42)
        at jdk.internal.vm.compiler/org.graalvm.compiler.phases.Phase.apply(Phase.java:38)
        at com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.parse(MethodTypeFlowBuilder.java:225)
        at com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.apply(MethodTypeFlowBuilder.java:352)
        at com.oracle.graal.pointsto.flow.MethodTypeFlow.doParse(MethodTypeFlow.java:322)
        at com.oracle.graal.pointsto.flow.MethodTypeFlow.ensureParsed(MethodTypeFlow.java:311)
        at com.oracle.graal.pointsto.flow.MethodTypeFlow.addContext(MethodTypeFlow.java:112)
        at com.oracle.graal.pointsto.DefaultAnalysisPolicy$DefaultVirtualInvokeTypeFlow.onObservedUpdate(DefaultAnalysisPolicy.java:228)
        at com.oracle.graal.pointsto.flow.TypeFlow.notifyObservers(TypeFlow.java:470)
        at com.oracle.graal.pointsto.flow.TypeFlow.update(TypeFlow.java:542)
        at com.oracle.graal.pointsto.BigBang$2.run(BigBang.java:530)
        at com.oracle.graal.pointsto.util.CompletionExecutor.lambda$execute$0(CompletionExecutor.java:173)
        at java.base/java.util.concurrent.ForkJoinTask$RunnableExecuteAction.exec(ForkJoinTask.java:1426)
        at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:290)
        at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1020)
        at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1656)
        at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1594)
        at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:177)
Error: Frame states being merged are incompatible: unbalanced monitors - locked objects do not match
 This frame state: [locals: [1,_,_] stack: [6] locks: [] rethrowException]
Other frame state: [locals: [1,_,_] stack: [20] locks: [13 / 4] rethrowException]
Parser context: clojure.spec.gen.alpha$dynaload$fn__2628.invoke(alpha.clj:22) [bci: 13, intrinsic: false]
  13: checkcast     #30         // clojure.lang.IFn
  16: getstatic     #5          // const__1:clojure.lang.Var
  19: invokevirtual #4          // clojure.lang.Var.getRawRoot:()java.lang.Object
  22: checkcast     #30         // clojure.lang.IFn

Call path from entry point to clojure.spec.gen.alpha$dynaload$fn__2628.invoke():
        at clojure.spec.gen.alpha$dynaload$fn__2628.invoke(alpha.clj:21)
        at clojure.lang.AFn.run(AFn.java:22)
        at java.lang.Thread.run(Thread.java:834)
        at com.oracle.svm.core.thread.JavaThreads.threadStartRoutine(JavaThreads.java:517)
        at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:193)
        at com.oracle.svm.core.code.IsolateEnterStub.PosixJavaThreads_pthreadStartRoutine_e1f4a8c0039f8337338252cd8734f63a79b5e3df(generated:0)
Original exception that caused the problem: org.graalvm.compiler.core.common.PermanentBailoutException: Frame states being merged are incompatible: unbalanced monitors - locked objects do not match
 This frame state: [locals: [1,_,_] stack: [6] locks: [] rethrowException]
Other frame state: [locals: [1,_,_] stack: [20] locks: [13 / 4] rethrowException]
Parser context: clojure.spec.gen.alpha$dynaload$fn__2628.invoke(alpha.clj:22) [bci: 13, intrinsic: false]
  13: checkcast     #30         // clojure.lang.IFn
  16: getstatic     #5          // const__1:clojure.lang.Var
  19: invokevirtual #4          // clojure.lang.Var.getRawRoot:()java.lang.Object
  22: checkcast     #30         // clojure.lang.IFn

        at jdk.internal.vm.compiler/org.graalvm.compiler.java.FrameStateBuilder.checkCompatibleWith(FrameStateBuilder.java:420)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.FrameStateBuilder.merge(FrameStateBuilder.java:433)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.createTarget(BytecodeParser.java:3172)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.appendGoto(BytecodeParser.java:3345)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.createExceptionDispatch(BytecodeParser.java:3295)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.processBlock(BytecodeParser.java:3228)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.build(BytecodeParser.java:1088)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.buildRootMethod(BytecodeParser.java:982)
        at jdk.internal.vm.compiler/org.graalvm.compiler.java.GraphBuilderPhase$Instance.run(GraphBuilderPhase.java:84)
        at jdk.internal.vm.compiler/org.graalvm.compiler.phases.Phase.run(Phase.java:49)
        at jdk.internal.vm.compiler/org.graalvm.compiler.phases.BasePhase.apply(BasePhase.java:214)
        at jdk.internal.vm.compiler/org.graalvm.compiler.phases.Phase.apply(Phase.java:42)
        at jdk.internal.vm.compiler/org.graalvm.compiler.phases.Phase.apply(Phase.java:38)
        at com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.parse(MethodTypeFlowBuilder.java:225)
        at com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.apply(MethodTypeFlowBuilder.java:352)
        at com.oracle.graal.pointsto.flow.MethodTypeFlow.doParse(MethodTypeFlow.java:322)
        at com.oracle.graal.pointsto.flow.MethodTypeFlow.ensureParsed(MethodTypeFlow.java:311)
        at com.oracle.graal.pointsto.flow.MethodTypeFlow.addContext(MethodTypeFlow.java:112)
        at com.oracle.graal.pointsto.DefaultAnalysisPolicy$DefaultVirtualInvokeTypeFlow.onObservedUpdate(DefaultAnalysisPolicy.java:228)
        at com.oracle.graal.pointsto.flow.TypeFlow.notifyObservers(TypeFlow.java:470)
        at com.oracle.graal.pointsto.flow.TypeFlow.update(TypeFlow.java:542)
        at com.oracle.graal.pointsto.BigBang$2.run(BigBang.java:530)
        at com.oracle.graal.pointsto.util.CompletionExecutor.lambda$execute$0(CompletionExecutor.java:173)
        at java.base/java.util.concurrent.ForkJoinTask$RunnableExecuteAction.exec(ForkJoinTask.java:1426)
        at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:290)
        at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1020)
        at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1656)
        at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1594)
        at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:177)

Warning: Use -H:+ReportExceptionStackTraces to print stacktrace of underlying exception
[simple_main:9930]    classlist:   1,494.10 ms,  0.96 GB
[simple_main:9930]        (cap):     871.25 ms,  0.96 GB
[simple_main:9930]        setup:   2,327.22 ms,  0.96 GB
[simple_main:9930]     (clinit):     172.29 ms,  1.21 GB
[simple_main:9930]   (typeflow):   4,625.47 ms,  1.21 GB
[simple_main:9930]    (objects):   3,842.76 ms,  1.21 GB
[simple_main:9930]   (features):     199.88 ms,  1.21 GB
[simple_main:9930]     analysis:   9,001.03 ms,  1.21 GB
[simple_main:9930]     universe:     399.27 ms,  1.21 GB
[simple_main:9930]      (parse):   1,132.15 ms,  1.21 GB
[simple_main:9930]     (inline):   1,326.67 ms,  1.66 GB
[simple_main:9930]    (compile):   7,011.48 ms,  2.27 GB
[simple_main:9930]      compile:  10,082.03 ms,  2.27 GB
[simple_main:9930]        image:   1,050.75 ms,  2.27 GB
[simple_main:9930]        write:     149.57 ms,  2.27 GB
[simple_main:9930]      [total]:  24,720.00 ms,  2.27 GB
Warning: Image 'simple_main' is a fallback image that requires a JDK for execution (use --no-fallback to suppress fallback image generation and to print more detailed information why a fallback image was necessary).
```