# hiccup

Testing whether [hiccup](https://github.com/weavejester/hiccup) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [hiccup "1.0.5"]

Test with:

    lein do clean, uberjar, native, run-native


## Results
`[hiccup.core]` :white_check_mark:
