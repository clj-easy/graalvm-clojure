# aleph+netty

Testing whether Aleph library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [aleph "0.4.7-alpha5"]
    (All Netty dependencies) "4.1.39.Final"

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

## Caveats

* This requires overriding Aleph's Netty dependencies to use Netty
  `4.1.39.Final`. Possibly older versions of Netty would also work,
  but Netty is adding improvements to native-image generation all the
  time, so I recommend using a recent version.
* Quite a few classes need to be initialized at runtime if you also
  set `--initialize-at-build-time`:
  `--initialize-at-run-time=io.netty.channel.epoll.EpollEventArray,io.netty.channel.unix.Errors,io.netty.channel.unix.IovArray,io.netty.channel.unix.Socket,io.netty.channel.epoll.Native,io.netty.channel.epoll.EpollEventLoop,io.netty.util.internal.logging.Log4JLogger`
* This won't work if you are trying to create a Netty server that
  serves TLS.

See https://medium.com/graalvm/updates-on-class-initialization-in-graalvm-native-image-generation-c61faca461f7
and https://github.com/cstancu/netty-native-demo for more info on
building a native image with Netty.

Additionally:

 - Native image compilation fails with **GraalVM-CE-Java11-20.0.0** but works with **GraalVM-CE-Java8-20.0.0**.
 this is the error message:
```
 Caused by: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException: No instances of java.io.FilePermission are allowed in the image heap as this class should be initialized at image runtime. To see how this object got instantiated use -H:+TraceClassInitialization.
Detailed message:
Trace: Object was reached by
	reading field java.util.concurrent.ConcurrentHashMap$Node.val of
		constant java.util.concurrent.ConcurrentHashMap$Node@e542855d reached by
	indexing into array
		constant java.util.concurrent.ConcurrentHashMap$Node[]@6547c011 reached by
	reading field java.util.concurrent.ConcurrentHashMap.table of
		constant java.util.concurrent.ConcurrentHashMap@e542855d reached by
	reading field java.io.FilePermissionCollection.perms of
		constant java.io.FilePermissionCollection@733c1c92 reached by
	reading field java.util.concurrent.ConcurrentHashMap$Node.val of
		constant java.util.concurrent.ConcurrentHashMap$Node@25c17b8c reached by
	indexing into array
		constant java.util.concurrent.ConcurrentHashMap$Node[]@1e34f8ee reached by
	reading field java.util.concurrent.ConcurrentHashMap.table of
		constant java.util.concurrent.ConcurrentHashMap@25c17b8c reached by
	reading field java.security.Permissions.permsMap of
		constant java.security.Permissions@4c99021a reached by
	reading field java.security.ProtectionDomain.permissions of
		constant java.security.ProtectionDomain@5785676d reached by
	indexing into array
		constant java.security.ProtectionDomain[]@7a1bf7a6 reached by
	reading field java.security.AccessControlContext.context of
		constant java.security.AccessControlContext@5785676d reached by
	reading field java.net.URLClassLoader.acc of
		constant clojure.lang.DynamicClassLoader@43e12bef reached by
	reading field java.lang.Class.classLoader of
		constant java.lang.Class@1cf8be3b reached by
	Hub

	at com.oracle.graal.pointsto.constraints.UnsupportedFeatures.report(UnsupportedFeatures.java:126)
	at com.oracle.svm.hosted.NativeImageGenerator.runPointsToAnalysis(NativeImageGenerator.java:738)
	... 8 more
Error: Image build request failed with exit status 1
```
