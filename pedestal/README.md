# pedestal

Testing whether [pedestal](https://github.com/pedestal/pedestal) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [io.pedestal/pedestal.service "0.5.10"]

Test with:

    lein do run, clean, uberjar, native, run-native

## Results

`[io.pedestal.http :as http]` :white_check_mark:
`[io.pedestal.jetty :as jetty]` :white_check_mark:
