# ring+jetty

Testing whether [Ring](https://github.com/ring-clojure/ring) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [ring/ring-core "1.8.0"]
    [ring/ring-jetty-adapter "1.8.0"]

Test with:

    lein do clean, uberjar, native, run-native

On a separate terminal:

``` text
$ curl -i http://localhost:3000/
HTTP/1.1 200 OK
Date: Sun, 12 May 2019 21:37:41 GMT
Content-Type: text/html
Content-Length: 13
Server: Jetty(9.4.12.v20180830)

Hello GraalVM
```

## Runtime failure

Currently using GraalVM 20, Ring-1.8.0 still getting a runtime error:

``` text
2020-02-22 17:42:56.571:WARN:oejs.HttpChannel:qtp2084389003-28: /
java.lang.IllegalArgumentException: No matching field found: getDispatcherType for class org.eclipse.jetty.server.Request
	at clojure.lang.Reflector.getInstanceField(Reflector.java:397)
	at clojure.lang.Reflector.invokeNoArgInstanceMember(Reflector.java:440)
	at ring.adapter.jetty$proxy_handler$fn__410.invoke(jetty.clj:25)
	at ring.adapter.jetty.proxy$org.eclipse.jetty.server.handler.AbstractHandler$ff19274a.handle(Unknown Source)
	at org.eclipse.jetty.server.handler.HandlerWrapper.handle(HandlerWrapper.java:127)
	at org.eclipse.jetty.server.Server.handle(Server.java:500)
	at org.eclipse.jetty.server.HttpChannel.lambda$handle$1(HttpChannel.java:386)
	at org.eclipse.jetty.server.HttpChannel.dispatch(HttpChannel.java:562)
	at org.eclipse.jetty.server.HttpChannel.handle(HttpChannel.java:378)
	at org.eclipse.jetty.server.HttpConnection.onFillable(HttpConnection.java:270)
	at org.eclipse.jetty.io.AbstractConnection$ReadCallback.succeeded(AbstractConnection.java:311)
	at org.eclipse.jetty.io.FillInterest.fillable(FillInterest.java:103)
	at org.eclipse.jetty.io.ChannelEndPoint$2.run(ChannelEndPoint.java:117)
	at org.eclipse.jetty.util.thread.strategy.EatWhatYouKill.runTask(EatWhatYouKill.java:336)
	at org.eclipse.jetty.util.thread.strategy.EatWhatYouKill.doProduce(EatWhatYouKill.java:313)
	at org.eclipse.jetty.util.thread.strategy.EatWhatYouKill.tryProduce(EatWhatYouKill.java:171)
	at org.eclipse.jetty.util.thread.strategy.EatWhatYouKill.run(EatWhatYouKill.java:129)
	at org.eclipse.jetty.util.thread.ReservedThreadExecutor$ReservedThread.run(ReservedThreadExecutor.java:388)
	at org.eclipse.jetty.util.thread.QueuedThreadPool.runJob(QueuedThreadPool.java:806)
	at org.eclipse.jetty.util.thread.QueuedThreadPool$Runner.run(QueuedThreadPool.java:938)
	at java.lang.Thread.run(Thread.java:834)
	at com.oracle.svm.core.thread.JavaThreads.threadStartRoutine(JavaThreads.java:527)
	at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:193)
```
