# clj-sophia

Testing whether [clj-sophia](https://github.com/BrunoBonacci/clj-sophia) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [com.brunobonacci/clj-sophia "0.5.2"]

Test with:

    lein do clean, uberjar, native, run-native


At runtime the following error is thrown:

    Exception in thread "main" java.lang.UnsatisfiedLinkError: Error looking up function 'sp_env': com.sun.jna.Native.findSymbol(JLjava/lang/String;)J [symbol: Java_com_sun_jna_Native_findSymbol or Java_com_sun_jna_Native_findSymbol__JLjava_lang_String_2]
        at com.sun.jna.Function.<init>(Function.java:251)
        at com.sun.jna.NativeLibrary.getFunction(NativeLibrary.java:566)
        at com.sun.jna.NativeLibrary.getFunction(NativeLibrary.java:542)
        at com.sun.jna.NativeLibrary.getFunction(NativeLibrary.java:528)
        at com.sun.jna.Library$Handler.invoke(Library.java:228)
        at com.sun.proxy.$Proxy180.sp_env(Unknown Source)
        at com.brunobonacci.sophia.native$sp_env.invokeStatic(native.clj:121)
        at com.brunobonacci.sophia.native$sp_env.invoke(native.clj:102)
        at com.brunobonacci.sophia$fn__9197$sophia__9207.invoke(sophia.clj:146)
        at simple.main$_main.invokeStatic(main.clj:9)
        at simple.main$_main.invoke(main.clj:7)
        at clojure.lang.AFn.applyToHelper(AFn.java:152)
        at clojure.lang.AFn.applyTo(AFn.java:144)
        at simple.main.main(Unknown Source)
