# timbre

Testing whether [timbre](https://github.com/ptaoussanis/timbre) can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [com.taoensso/timbre "5.1.2"]

Test with:

    lein do clean, uberjar, native, run-native


## Results
`[taoensso.timbre]`  :white_check_mark:  
`[taoensso.timbre.appenders.core]`  :white_check_mark:  
