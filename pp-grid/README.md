# pp-grid

Testing whether [pp-grid](https://github.com/rorokimdim/pp-grid) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [org.clojars.rorokimdim/pp-grid "0.1.14"]

Test with:

    lein do clean, uberjar, native, run-native

