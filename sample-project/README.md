# Sample-project

Use this project as template for testing a specific library with GraalVM **native-image**

## Pre-requisite

- Valid installation of GRAALVM 
- Install `native-image` at least once e.g. `$GRAALVM_HOME/bin/gu native-image`
- Setup the env for GRAALVM_HOME e.g. `export GRAALVM_HOME=/path/to/your/graalvm-installation`
- Valid Java installation e.g. `export JAVA_HOME=/path/to/your/java-installation`

## Usage

Currently testing:

    [add.your/library-here "0.1.0]

Test with:

    lein do clean, uberjar, native, run-native

Add any info might be useful for the reader.
