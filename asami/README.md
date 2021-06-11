# Asami

Use this project as template for testing a specific library with GraalVM **native-image**

## Usage

Currently testing:

    [org.clojars.quoll/asami "2.0.6"]

Test with:

    lein do clean, uberjar, native, run-native


This all works. Not included is the use of asami:local://name URLs as this will create
a new directory to hold the indexes.
