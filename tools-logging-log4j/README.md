# clojure/tools.logging

Testing whether [clojure/tools.logging](https://github.com/clojure/tools.logging) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

tested with GraalVM CE 22.1.0

    [org.clojure/tools.logging "0.4.1"]
    [org.apache.logging.log4j/log4j-core "2.18.0"]
    ;; also SLF4j works with this configuration
    ;;[org.slf4j/slf4j-log4j12 "1.7.36"]



Test with:

    lein do clean, uberjar, native, run-native
