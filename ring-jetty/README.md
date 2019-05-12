# ring+jetty

Testing whether Ring library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [ring/ring-core "1.7.1"]
    [ring/ring-jetty-adapter "1.7.1"]

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
