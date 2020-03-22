# http-kit

Testing whether [http-kit](https://github.com/http-kit/http-kit) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [http-kit "2.3.0"]

Test with:

    lein do clean, uberjar, native, run-native

On a separate terminal:

``` text
$ curl -i http://localhost:3000/
HTTP/1.1 200 OK
Content-Type: text/html
Content-Length: 13
Server: http-kit
Date: Sun, 22 Mar 2020 12:39:33 GMT

Hello GraalVM
```

## Results
`[org.httpkit.server :as server]` :white_check_mark:.   
`[org.httpkit.client :as client]` :x:

## Notes on http-kit server
**GraalVM-CE-Java8-20.0.0** is preferred when using `http-kit` as a server.    
The native image compilation succeed with both **GraalVM-CE-Java11-20.0.0** and **GraalVM-CE-Java8-20.0.0** in this example project, however in a fully fledged application **GraalVM-CE-Java11-20.0.0** fails to compile.   
**GraalVM-CE-Java8-20.0.0** succeeds in both cases.

```
Error: No instances of java.io.FilePermission are allowed in the image heap as this class should be initialized at image runtime. To see how this object got instantiated use -H:+TraceClassInitialization.
Detailed message:
Trace: Object was reached by 
	reading field java.util.concurrent.ConcurrentHashMap$Node.val of
		constant java.util.concurrent.ConcurrentHashMap$Node@8dde7265 reached by 
	indexing into array
		constant java.util.concurrent.ConcurrentHashMap$Node[]@6c77f2c9 reached by 
	reading field java.util.concurrent.ConcurrentHashMap.table of
		constant java.util.concurrent.ConcurrentHashMap@8dde7265 reached by 
	reading field java.io.FilePermissionCollection.perms of
		constant java.io.FilePermissionCollection@6cd327ba reached by 
	reading field java.util.concurrent.ConcurrentHashMap$Node.val of
		constant java.util.concurrent.ConcurrentHashMap$Node@1b3aa7c5 reached by 
	indexing into array
		constant java.util.concurrent.ConcurrentHashMap$Node[]@4ade4a40 reached by 
	reading field java.util.concurrent.ConcurrentHashMap.table of
		constant java.util.concurrent.ConcurrentHashMap@1b3aa7c5 reached by 
	reading field java.security.Permissions.permsMap of
		constant java.security.Permissions@4005e013 reached by 
	reading field java.security.ProtectionDomain.permissions of
		constant java.security.ProtectionDomain@23282c25 reached by 
	indexing into array
		constant java.security.ProtectionDomain[]@13f78d05 reached by 
	reading field java.security.AccessControlContext.context of
		constant java.security.AccessControlContext@23282c25 reached by 
	reading field java.net.URLClassLoader.acc of
		constant clojure.lang.DynamicClassLoader@323323e3 reached by 
	reading field java.lang.Class.classLoader of
		constant java.lang.Class@6d8f5a95 reached by 
	Hub

Error: Use -H:+ReportExceptionStackTraces to print stacktrace of underlying exception
Error: Image build request failed with exit status 1
```

## Notes on http-kit client
Currently using GraalVM 20, the `http-kit` client fails to compile. 

```
Warning: Aborting stand-alone image build. Unsupported features in 2 methods
Detailed message:
Error: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException: No instances of javax.net.ssl.SSLContext are allowed in the image heap as this class should be initialized at image runtime. To see how this object got instantiated use -H:+TraceClassInitialization.
Trace: 
	at parsing org.httpkit.client.HttpClient.exec(HttpClient.java:347)
Call path from entry point to org.httpkit.client.HttpClient.exec(String, RequestConfig, SSLEngine, IRespListener): 
	at org.httpkit.client.HttpClient.exec(HttpClient.java:269)
	at org.httpkit.client$request.invokeStatic(client.clj:258)
	at org.httpkit.client$request.doInvoke(client.clj:152)
	at clojure.lang.RestFn.applyTo(RestFn.java:139)
	at simple.main.main(Unknown Source)
	at com.oracle.svm.core.JavaMainWrapper.runCore(JavaMainWrapper.java:151)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:186)
	at com.oracle.svm.core.code.IsolateEnterStub.JavaMainWrapper_run_5087f5482cc9a6abc971913ece43acb471d2631b(generated:0)
Error: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException: No instances of javax.net.ssl.SSLContext are allowed in the image heap as this class should be initialized at image runtime. To see how this object got instantiated use -H:+TraceClassInitialization.
Trace: 
	at parsing org.httpkit.client.SslContextFactory.trustAnybody(SslContextFactory.java:36)
Call path from entry point to org.httpkit.client.SslContextFactory.trustAnybody(): 
	at org.httpkit.client.SslContextFactory.trustAnybody(SslContextFactory.java:36)
	at org.httpkit.client$coerce_req.invokeStatic(client.clj:68)
	at org.httpkit.client$coerce_req.invoke(client.clj:59)
	at clojure.instant.proxy$java.lang.ThreadLocal$ff19274a.hashCode(Unknown Source)
	at java.util.HashMap.hash(HashMap.java:339)
	at java.util.HashMap.get(HashMap.java:552)
	at com.oracle.svm.jni.access.JNIReflectionDictionary.getFieldNameByID(JNIReflectionDictionary.java:278)
	at com.oracle.svm.jni.functions.JNIFunctions.ToReflectedField(JNIFunctions.java:856)
	at com.oracle.svm.core.code.IsolateEnterStub.JNIFunctions_ToReflectedField_80d8233579d5215df0227b770e5c01228a0de9b9(generated:0)

Warning: Use -H:+ReportExceptionStackTraces to print stacktrace of underlying exception
```