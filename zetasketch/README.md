# Sample-project

Use this project as template for testing a specific library with GraalVM **native-image**

## Usage

Currently testing:

    [com.google.zetasketch/zetasketch "0.1.0"]

Test with:

    lein do clean, uberjar, native, run-native


Add any info might be useful for the reader.


## Error

Zetasketch doesn't work due to ProtoBuf serialize error.

```
"RESULT1:" 3
"DATA1:" [0 0 0 0 0 0 0 0 8 112 16 3 24 2 32 11 -126 7 17 -85 -36 7 -57 -105 10 -39 -93 34]
Exception in thread "main" java.lang.IllegalArgumentException: com.google.zetasketch.shaded.com.google.protobuf.InvalidProtocolBufferException: Protocol message contained an invalid tag (zero).
	at com.google.zetasketch.HyperLogLogPlusPlus.forProto(HyperLogLogPlusPlus.java:131)
	at com.google.zetasketch.HyperLogLogPlusPlus.forProto(HyperLogLogPlusPlus.java:119)
	at simple.main$deserialize_from_bytes.invokeStatic(main.clj:18)
	at simple.main$deserialize_from_bytes.invoke(main.clj:16)
	at simple.main$_main.invokeStatic(main.clj:33)
	at simple.main$_main.invoke(main.clj:27)
	at clojure.lang.AFn.applyToHelper(AFn.java:152)
	at clojure.lang.AFn.applyTo(AFn.java:144)
	at simple.main.main(Unknown Source)
Caused by: com.google.zetasketch.shaded.com.google.protobuf.InvalidProtocolBufferException: Protocol message contained an invalid tag (zero).
	at com.google.zetasketch.shaded.com.google.protobuf.CodedInputStream$ArrayDecoder.readTag(CodedInputStream.java:652)
	at com.google.zetasketch.internal.hllplus.State.parse(State.java:197)
	at com.google.zetasketch.HyperLogLogPlusPlus.forProto(HyperLogLogPlusPlus.java:128)
	... 8 more
```

The problem seems to be the leading "0" in DATA1: `0 0 0 0 0 0 0 0`

similar issues on the web: https://github.com/quarkusio/quarkus/issues/35125
