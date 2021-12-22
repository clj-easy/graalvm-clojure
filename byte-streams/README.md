# Sample-project

Use this project as template for testing a specific library with GraalVM **native-image**

## Usage

Currently testing:

    [org.clj-commons/byte-streams "0.2.10"]

Test with:

    lein do clean, uberjar, native, run-native

If you want to avoid the warning about `--initialize-at-build-time` try with:

    lein do clean, uberjar+graal-build-time, native, run-native
