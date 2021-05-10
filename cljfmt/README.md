# cljfmt

Use this project as template for testing a specific library with GraalVM **native-image**

## Usage

Currently testing:

    [cljfmt "0.7.0"]

Test with:

    lein do clean, uberjar, native, run-native

## Results
`[cljfmt.main :as fmt]` :white_check_mark:.
