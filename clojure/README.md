# Clojure core and GraalVM

It attempts to build a simple main and build a native image under several Clojure versions and GraalVM versions:

``` clojure
(ns simple.main
  (:gen-class))

(defn -main [& args]
  (println "Hello GraalVM!"))
```

## Usage

Build the standalone executable jar with

    lein with-profile clojure-1.10.0 do clean, uberjar

Run with

    lein with-profile clojure-1.10.0 run

or using the jar

    java -jar ./target/simple-clojure-1.10.0-uberjar.jar

Build all the Clojure versions and native images with

    # list your GraalVM versions home directory
    cat > graal.paths <<EOF
    /full/path/to/my/graalvm/ver1/home
    /full/path/to/my/graalvm/ver2/home
    /full/path/to/my/graalvm/ver3/home
    EOF
    # Build and test all Clojure / GraalVM combinations
    ./bin/build-all.sh

The artifacts will be store in `./out` folder, including the uberjars.

Currently testing the following Clojure versions

  - Clojure 1.7.0
  - Clojure 1.8.0
  - Clojure 1.9.0
  - Clojure 1.10.0
  - Clojure 1.10.1

And the following GraalVM versions

  - GraalVM-CE-1.0.0-rc14
  - GraalVM-CE-1.0.0-rc15
  - GraalVM-CE-1.0.0-rc16
  - GraalVM-CE-19.0.0
  - GraalVM-CE-19.3.0.2
  - GraalVM-CE-19.3.1


This is the current result:

``` text
----------------------------------------------------------------------
                      Verifying native images
----------------------------------------------------------------------
verifying simple-clojure-1.10.1-graal-19.3.1       : OK
verifying simple-clojure-1.10.0-graal-19.3.1       : OK
verifying simple-clojure-1.9.0-graal-19.3.1        : OK
verifying simple-clojure-1.8.0-graal-19.3.1        : OK
verifying simple-clojure-1.7.0-graal-19.3.1        : OK
verifying simple-clojure-1.10.1-graal-19.3.0.2     : OK
verifying simple-clojure-1.10.0-graal-19.3.0.2     : OK
verifying simple-clojure-1.9.0-graal-19.3.0.2      : OK
verifying simple-clojure-1.8.0-graal-19.3.0.2      : OK
verifying simple-clojure-1.7.0-graal-19.3.0.2      : OK
verifying simple-clojure-1.10.1-graal-19.0.0       : OK
verifying simple-clojure-1.10.0-graal-19.0.0       : OK
verifying simple-clojure-1.9.0-graal-19.0.0        : OK
verifying simple-clojure-1.8.0-graal-19.0.0        : OK
verifying simple-clojure-1.7.0-graal-19.0.0        : OK
verifying simple-clojure-1.10.0-graal-1.0.0-rc16   : OK
verifying simple-clojure-1.9.0-graal-1.0.0-rc16    : OK
verifying simple-clojure-1.8.0-graal-1.0.0-rc16    : OK
verifying simple-clojure-1.7.0-graal-1.0.0-rc16    : OK
verifying simple-clojure-1.10.0-graal-1.0.0-rc15   : OK
verifying simple-clojure-1.9.0-graal-1.0.0-rc15    : OK
verifying simple-clojure-1.8.0-graal-1.0.0-rc15    : OK
verifying simple-clojure-1.7.0-graal-1.0.0-rc15    : OK
verifying simple-clojure-1.10.0-graal-1.0.0-rc14   : OK
verifying simple-clojure-1.9.0-graal-1.0.0-rc14    : OK
verifying simple-clojure-1.8.0-graal-1.0.0-rc14    : OK
verifying simple-clojure-1.7.0-graal-1.0.0-rc14    : OK
```
