# Sample-project

Use this project as template for testing a specific library with GraalVM **native-image**

testing: https://roaringbitmap.org/ , some basic operations, serialization and deserialization.

Tested on:

    Java version: 20.0.2+9, vendor version: Oracle GraalVM 20.0.2+9.1
    Graal compiler: optimization level: b, target machine: compatibility, PGO: off
    C compiler: cc (apple, x86_64, 15.0.0)

and

    Version info: 'GraalVM 22.3.3 Java 17 CE'
    Java version info: '17.0.8+7-jvmci-22.3-b22'
    C compiler: gcc (redhat, x86_64, 11.3.1)

## Usage

Currently testing:

    [org.roaringbitmap/RoaringBitmap "1.0.0"]

Test with (requires a local GraalVM installation):

    lein do clean, uberjar, native, run-native

alternatively use a Dockerized versions:

    docker build --progress=plain -t graalvm-test .


Add any info might be useful for the reader.
