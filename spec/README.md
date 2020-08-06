# Spec Graal

Tests for using the Clojure spec.alpha library with GraalVM **native-image**.

## Usage

Currently testing:

    [org.clojure/spec.alpha "0.2.187"]

We can successfully build the native image at `target/spec-graal` with:

    $ lein do clean, uberjar, native, run-native

The following is equivalent:

    $ lein do clean, uberjar
    $ native-image \
          --report-unsupported-elements-at-runtime \
          --no-server \
          --initialize-at-build-time \
          -jar ./target/spec-graal.jar \
          -H:Name=./target/spec-graal

Using tools.deps, the native image can be built as follows:

    $ clojure -A:depstar -m hf.depstar.uberjar target/spec-graal.jar -C -m spec-graal.main
    $ native-image \
          --report-unsupported-elements-at-runtime \
          --no-server \
          --initialize-at-build-time \
          -jar ./target/spec-graal.jar \
          -H:Name=./target/spec-graal

Speed difference:

    $ time ./target/spec-graal
    Hello GraalVM with Spec! Btw, 'Spec Graal' is a valid ::title.
    0.00 real         0.00 user         0.00 sys

    $ time java -jar ./target/spec-graal.jar
    Hello GraalVM with Spec! Btw, 'Spec Graal' is a valid ::title.
    0.95 real         1.86 user         0.19 sys
