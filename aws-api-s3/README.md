# cognitect/aws-api+s3

Testing whether cognitect/aws-api+s3 library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [com.cognitect.aws/api       "0.8.301"]
    [com.cognitect.aws/endpoints "1.1.11.537"]
    [com.cognitect.aws/s3        "714.2.430.0"]

Test with:

    lein do clean, uberjar, native, run-native
