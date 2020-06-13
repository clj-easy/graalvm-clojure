# Sample-project

Use this project as template for testing a specific library with GraalVM **native-image**

## Usage

Currently testing:

    [com.stuartsierra/component "1.0.0"]

Test with:

    lein do clean, uberjar, native, run-native


Add any info might be useful for the reader.

Works with and without direct linking: https://epiccastle.io/blog/faster-graalvm-clojure-compilation-times/
