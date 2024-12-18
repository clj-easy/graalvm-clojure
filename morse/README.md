# Sample-project

Use this project as template for testing a specific library with GraalVM **native-image**

## Usage

Currently testing:

    [add.your/library-here "0.1.0]

Test with (requires a local GraalVM installation):

    lein do clean, uberjar, native, run-native

alternatively use a Dockerized versions:

    docker build --progress=plain -t graalvm-test .


Add any info might be useful for the reader.
