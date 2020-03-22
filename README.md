# Clojure meets GraalVM

This project contains a set of "hello world" projects to verify which
Clojure libraries do actually compile and produce **native images**
under GraalVM.

Here the instructions on how to build your own Clojure projects with GraalVM.

  - [Download a GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases)
  - [Build a "Hello world!" project](./doc/clojure-graalvm-native-binary.md)


Here the list of libraries tested:

  - [Clojure core](./clojure) :white_check_mark:
  - [clojure/tools.logging](./tools-logging) - Logging library :white_check_mark:
  - [clojure/tools.logging+log4j](./tools-logging-log4j) - Logging library :white_check_mark:
  - [amazonica+s3](./amazonica-s3) - Cloud API wrapper library :x: (*Buildtime and Runtime error*)
  - [cheshire](./cheshire) - JSON parser/writer :white_check_mark:
  - [clj-sophia](./clj-sophia) - A fast RAM-Disk hybrid storage :x: (Runtime error/JNA)
  - [nippy](./nippy) - Clojure serialization/deserialization library :warning: (*Can't serialize exceptions*)
  - cognitect/aws-api+s3 - Cloud API library :question:
  - [ring/jetty](./ring-jetty) - Web server :x: (*Runtime error*)
  - [http-kit](./http-kit) - Web server :white_check_mark:
  - [aleph](./aleph) - Web server :white_check_mark:
  - [safely](./safely) - Circuit breaker :white_check_mark:
  - [secure-random](./secure-random) - `SecureRandom` initialization :white_check_mark:


More libraries to come (*PRs are welcome*).

Interesting GraalVM documentation to build native-images:

  - Understand [Class Initialization in Native Image](https://github.com/oracle/graal/blob/master/substratevm/CLASS-INITIALIZATION.md)
  - [Assisted Configuration of Native Image Builds](https://github.com/oracle/graal/blob/master/substratevm/CONFIGURE.md)
  - [URL Protocols on Substrate VM](https://github.com/oracle/graal/blob/master/substratevm/URL-PROTOCOLS.md) for `http` and `https`
  - [JCA Security Services on Substrate VM](https://github.com/oracle/graal/blob/master/substratevm/JCA-SECURITY-SERVICES.md)


## How to contribute

If you wish to add a library and contribute with a PR please follow these steps:

  1. Fork the project
  2. use the `sample-project` as a template. `cp -r sample-project my-library`
  3. add the library to the `project.clj` and the **native-image** parameters used
  4. amend the `my-library/src/simple/main.clj` to use the library
  5. amend the `my-library/README.md` with the info you discovered.
  6. Make a PR with your findings and "Thank you" in advance.

## License

Copyright © 2019-2020 Bruno Bonacci

Distributed under the Apache License v 2.0 (http://www.apache.org/licenses/LICENSE-2.0)
