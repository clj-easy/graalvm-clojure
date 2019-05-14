# clojure/tools.logging

Testing whether [clojure/tools.logging](https://github.com/clojure/tools.logging) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [org.clojure/tools.logging "0.4.1"]

Test with:

    lein do clean, uberjar, native, run-native
