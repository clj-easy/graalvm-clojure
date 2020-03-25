# clostache

Testing whether [clostache](https://github.com/fhd/clostache) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [de.ubercode.clostache/clostache "1.4.0"]

Test with:

    lein do clean, uberjar, native, run-native

```

## Results
`[clostache.parser :as clo]` :white_check_mark:.  

## Notes
- To get clostache to working a [reflect-config](./reflect-config.json) file is necessary. Once this is available it compiles and runs. 