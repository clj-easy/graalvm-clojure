# aleph+netty

Testing whether Aleph library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [aleph "0.4.7-alpha5"]
    (All Netty dependencies) "4.1.39.Final"

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

* This requires overriding Aleph's Netty dependencies to use Netty
  `4.1.39.Final`. Possibly older versions of Netty would also work,
  but Netty is adding improvements to native-image generation all the
  time, so I recommend using a recent version.
* Quite a few classes need to be initialized at runtime if you also
  set `--initialize-at-build-time`:
  `--initialize-at-run-time=io.netty.channel.epoll.EpollEventArray,io.netty.channel.unix.Errors,io.netty.channel.unix.IovArray,io.netty.channel.unix.Socket,io.netty.channel.epoll.Native,io.netty.channel.epoll.EpollEventLoop,io.netty.util.internal.logging.Log4JLogger`
* This won't work if you are trying to create a Netty server that
  serves TLS.

See https://medium.com/graalvm/updates-on-class-initialization-in-graalvm-native-image-generation-c61faca461f7
and https://github.com/cstancu/netty-native-demo for more info on
building a native image with Netty.

Additionally:

 - Native image compilation works with **GraalVM-CE-Java11-20.1.0** and **GraalVM-CE-Java8-20.0.0**.
