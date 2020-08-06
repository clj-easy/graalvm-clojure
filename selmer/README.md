# Selmer

Testing whether [Selmer](https://github.com/yogthos/Selmer) can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [selmer "1.12.18"]

Test with:

    lein do clean, uberjar, native, run-native


## Results
`[selmer.parser]`  :white_check_mark:  
`[selmer.filters]` :white_check_mark:
