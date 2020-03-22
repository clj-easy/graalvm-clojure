# aleph+netty

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
Date: Sun, 22 Mar 2020 12:05:42 GMT

Hello GraalVM
```

## Notes
**GraalVM-CE-Java8-20.0.0** is preferred for `http-kit`. 
The native image compilation succeed with both **GraalVM-CE-Java11-20.0.0** and **GraalVM-CE-Java8-20.0.0** in this example project, however in a fully fledged application **GraalVM-CE-Java11-20.0.0** fails to compile. **GraalVM-CE-Java8-20.0.0** succeeds in both cases.

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