# cheshire

Testing whether [Cheshire](https://github.com/dakrone/cheshire) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [cheshire "5.8.1"]

Test with:

    lein do clean, uberjar, native, run-native
