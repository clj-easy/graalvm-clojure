# Nippy

Testing whether [Nippy](https://github.com/ptaoussanis/nippy) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

``` clojure
[com.taoensso/nippy "2.14.0"]
```

Test with:

``` clojure
lein do clean, uberjar, native, run-native
```

It works only without any configuration with Clojure data types.

For extra types included in stress data the additional configuration is necessary that works with the newest GraalVM DEV

https://github.com/graalvm/graalvm-ce-dev-builds/releases/tag/21.3.0-dev-20210914_2058

``` clojure
(defn -main []
  (prn (nippy/freeze nippy/stress-data))
  (prn (nippy/thaw (nippy/freeze nippy/stress-data))))
```


