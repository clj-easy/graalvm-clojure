# Clojure meets GraalVM

This project contains a set of "hello world" projects to verify which
Clojure libraries do actually compile and produce **native images**
under GraalVM.

Here the instructions on how to build your own Clojure projects with GraalVM.

  - [Build a "Hello world!" project](./doc/clojure-graalvm-native-binary.md)


Here the list of libraries tested:

  - [Clojure core](./clojure) :white_check_mark:
  - [clojure/tools.logging](./tools-logging) - Logging library :white_check_mark:
  - [clojure/tools.logging+log4j](./tools-logging-log4j) - Logging library :white_check_mark:
  - [amazonica+s3](./amazonica-s3) - Cloud API wrapper library :x: (*Buildtime and Runtime error*)
  - [cheshire](./cheshire) - JSON parser/writer :white_check_mark:
  - [nippy](./nippy) - Clojure serialization/deserialization library :warning: (*Can't serialize exceptions*)
  - cognitect/aws-api+s3 - Cloud API library :question:
  - ring/jetty - Web server :question:
  - ring/http-kit - Web server :question:
  - ring/aleph - Web server :question:
  - [safely](./safely) - Circuit breaker :white_check_mark:


More libraries to come (*PRs are welcome*).


## License

Copyright © 2019 Bruno Bonacci

Distributed under the Apache License v 2.0 (http://www.apache.org/licenses/LICENSE-2.0)
