# amazonica-s3

Testing whether [Amazonica/s3](https://github.com/mcohen01/amazonica) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [amazonica "0.3.142"]

Test with:

    lein do clean, uberjar, native, run-native

To be able to run this need to provide AWS credentials:

    export AWS_ACCESS_KEY_ID=xxx
    export AWS_SECRET_ACCESS_KEY=yyyy
    export AWS_DEFAULT_REGION=eu-west-1

However with `clojure-1.10.0` the native image compilation shows the
following warning message.

    Build on Server(pid: 77850, port: 49942)
    [./target/amazonica-s3:77850]    classlist:   2,083.42 ms
    [./target/amazonica-s3:77850]        (cap):     933.91 ms
    [./target/amazonica-s3:77850]        setup:   1,203.25 ms
    [./target/amazonica-s3:77850]     analysis:  26,775.72 ms
    Warning: Aborting stand-alone image build. unbalanced monitors: mismatch at monitorexit, 96|LoadField#lockee__5436__auto__ != 3|LoadField#lockee__5436__auto__
    Detailed message:
    Call path from entry point to clojure.spec.gen.alpha$dynaload$fn__2628.invoke():
        at clojure.spec.gen.alpha$dynaload$fn__2628.invoke(alpha.clj:21)
        at clojure.lang.AFn.applyToHelper(AFn.java:152)
        at clojure.lang.AFn.applyTo(AFn.java:144)
        at simple.main.main(Unknown Source)
        at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:153)
        at com.oracle.svm.core.code.IsolateEnterStub.JavaMainWrapper_run_5087f5482cc9a6abc971913ece43acb471d2631b(generated:0)
    Warning: Use -H:+ReportExceptionStackTraces to print stacktrace of underlying exception
    Build on Server(pid: 77850, port: 49942)
    [./target/amazonica-s3:77850]    classlist:     271.31 ms
    [./target/amazonica-s3:77850]        (cap):   1,048.31 ms
    [./target/amazonica-s3:77850]        setup:   1,342.62 ms
    [./target/amazonica-s3:77850]   (typeflow):   1,530.36 ms
    [./target/amazonica-s3:77850]    (objects):     447.20 ms
    [./target/amazonica-s3:77850]   (features):      87.13 ms
    [./target/amazonica-s3:77850]     analysis:   2,103.02 ms
    [./target/amazonica-s3:77850]     (clinit):      69.69 ms
    [./target/amazonica-s3:77850]     universe:     170.83 ms
    [./target/amazonica-s3:77850]      (parse):     251.07 ms
    [./target/amazonica-s3:77850]     (inline):     538.56 ms
    [./target/amazonica-s3:77850]    (compile):   1,937.14 ms
    [./target/amazonica-s3:77850]      compile:   2,878.45 ms
    [./target/amazonica-s3:77850]        image:     229.01 ms
    [./target/amazonica-s3:77850]        write:     138.33 ms
    [./target/amazonica-s3:77850]      [total]:   7,191.94 ms
    Warning: Image './target/amazonica-s3' is a fallback image that requires a JDK for execution (use --no-fallback to suppress fallback image generation).

And the execution of the native image shows:

    Error: Could not find or load main class simple.main


Using `clojure-1.8.0` the native image builds successfully however the native binary runs with the following error.

    Exception in thread "main" java.lang.NullPointerException
        at amazonica.core$constructor_args.invokeStatic(core.clj:415)
        at amazonica.core$constructor_args.invoke(core.clj:413)
        at amazonica.core$new_instance.invokeStatic(core.clj:445)
        at amazonica.core$new_instance.invoke(core.clj:438)
        at amazonica.core$create_bean.invokeStatic(core.clj:644)
        at amazonica.core$create_bean.invoke(core.clj:642)
        at amazonica.core$get_client_configuration.invokeStatic(core.clj:224)
        at amazonica.core$get_client_configuration.invoke(core.clj:221)
        at amazonica.core$amazon_client_STAR_.invokeStatic(core.clj:277)
        at amazonica.core$amazon_client_STAR_.invoke(core.clj:274)
        at clojure.lang.AFn.applyToHelper(AFn.java:160)
        at clojure.lang.AFn.applyTo(AFn.java:144)
        at clojure.core$apply.invokeStatic(core.clj:646)
        at clojure.core$memoize$fn__5708.doInvoke(core.clj:6107)
        at clojure.lang.RestFn.invoke(RestFn.java:436)
        at amazonica.core$candidate_client$fn__329.invoke(core.clj:858)
        at clojure.lang.Delay.deref(Delay.java:37)
        at clojure.core$deref.invokeStatic(core.clj:2228)
        at clojure.core$deref.invoke(core.clj:2214)
        at amazonica.core$candidate_client.invokeStatic(core.clj:859)
        at amazonica.core$candidate_client.invoke(core.clj:842)
        at amazonica.core$fn_call$fn__337.invoke(core.clj:872)
        at clojure.lang.Delay.deref(Delay.java:37)
        at clojure.core$deref.invokeStatic(core.clj:2228)
        at clojure.core$deref.invoke(core.clj:2214)
        at amazonica.core$fn_call$fn__339.invoke(core.clj:875)
        at amazonica.core$intern_function$fn__375.doInvoke(core.clj:1031)
        at clojure.lang.RestFn.invoke(RestFn.java:408)
        at simple.main$_main.invokeStatic(main.clj:8)
        at simple.main$_main.invoke(main.clj:7)
        at clojure.lang.AFn.applyToHelper(AFn.java:152)
        at clojure.lang.AFn.applyTo(AFn.java:144)
        at simple.main.main(Unknown Source)

More investigation is required.
