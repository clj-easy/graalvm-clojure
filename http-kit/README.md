# http-kit

Testing whether [http-kit](https://github.com/http-kit/http-kit) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [http-kit "2.5.0-alpha2"]

Test with:

    lein do clean, uberjar, native, run-native

## Results
`[org.httpkit.server :as server]` :white_check_mark:  
`[org.httpkit.client :as client]` :white_check_mark:
