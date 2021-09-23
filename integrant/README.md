# Integrant

Testing whether [integrant](https://github.com/weavejester/integrant) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [integrant "0.8.0"]

Test with:

    lein do clean, uberjar, native, run-native


