# cheshire

Testing whether Cheshire library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [cheshire "5.8.1"]

Test with:

    lein do clean, uberjar, native, run-native
