# datascript

Testing whether [datascript](https://github.com/tonsky/datascript) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [datascript "1.0.3"]
    [org.clojure/clojure "1.10.2"]

Test with:

    lein do clean, uberjar, native, run-native

## Results
`[datascript.core :as d]` :white_check_mark:  

## Notes
- To get datascript to compile a [reflect-config](./reflect-config.json) file is necessary. Once this is available it compiles and runs. 
```
