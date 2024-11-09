# pedestal

Testing whether [pedestal](https://github.com/pedestal/pedestal) library can be used in a native binary image with
GraalVM(23).

## Usage

Currently testing:

    [org.clojure/clojure "1.12.0"]
    [io.pedestal/pedestal.service "0.7.2"]
    [io.pedestal/pedestal.jetty "0.7.2"]

Test with:

    lein do run, clean, uberjar, native, run-native

## Results

`[io.pedestal.http :as http]` :white_check_mark:
`[io.pedestal.jetty :as jetty]` :white_check_mark:
`[io.pedestal.http.body-params :as body-params]` :white_check_mark:
`[io.pedestal.interceptor :as pedestal.interceptor]` :white_check_mark:
