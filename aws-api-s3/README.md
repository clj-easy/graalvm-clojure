# cognitect/aws-api+s3

Testing whether [cognitect/aws-api+s3](https://github.com/cognitect-labs/aws-api) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [com.cognitect.aws/api       "0.8.692"]
    [com.cognitect.aws/endpoints "1.1.12.772"]
    [com.cognitect.aws/s3        "869.2.1687.0"]

Gotchas:

`aws-api` uses
[`dynaload`](https://github.com/cognitect-labs/aws-api/blob/master/src/cognitect/aws/dynaload.clj)
to load dynamically resources and protocols at runtime. Avoid as much
as you can the use of `dynaload` by pre-loading the required
resources.

For example if during the compilation with `native-image` you see a bunch of WARNING messages like:

```
WARNING: Could not resolve cognitect.aws.protocols.rest_xml$eval151 for reflection configuration.
```

Then just require the namespace directly (see `simple.main`)

Finally, you need to pre-create a `http-client` for the same reason and pass it to all your
aws clients. For this you can use Clojure's `delay`, to ensure runtime initialization.

Test with:

    lein do clean, uberjar, native-config, native, run-native
