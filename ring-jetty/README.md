# ring+jetty

Testing whether [Ring](https://github.com/ring-clojure/ring) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [ring/ring-core "1.7.1"]
    [ring/ring-jetty-adapter "1.8.0"]

Test with:

    lein do clean, uberjar, native, run-native

On a separate terminal:

``` text
$ curl -i http://localhost:3000/
HTTP/1.1 200 OK
Date: Sun, 12 May 2019 21:37:41 GMT
Content-Type: text/html
Content-Length: 13
Server: Jetty(9.4.12.v20180830)

Hello GraalVM
```

## Caveats

* You need to use ring-jetty-adapter 1.8.0 to get [ring-clojure/ring#381](https://github.com/ring-clojure/ring/pull/381) 
