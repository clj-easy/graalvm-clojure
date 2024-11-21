# Clojure meets GraalVM

This project contains a set of "hello world" projects to verify which
Clojure libraries do actually compile and produce **native images**
under GraalVM.

Here the instructions on how to build your own Clojure projects with GraalVM.

  - [Download a GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases)
  - [Build a "Hello world!" project](./doc/clojure-graalvm-native-binary.md)


Here the list of libraries tested:

|       Status       | Library                                              | Description                                                                                       | Remarks                        |
|:------------------:|------------------------------------------------------|---------------------------------------------------------------------------------------------------|--------------------------------|
| :white_check_mark: | [Clojure core](./clojure)                            | Clojure core                                                                                      |                                |
| :white_check_mark: | [clojure spec](./spec)                               | Clojure Spec                                                                                      |                                |
| :white_check_mark: | [clojure/tools.logging](./tools-logging)             | Logging library                                                                                   |                                |
| :white_check_mark: | [clojure/tools.logging+log4j](./tools-logging-log4j) | Logging library                                                                                   |                                |
| :white_check_mark: | [aleph](./aleph)                                     | Web server                                                                                        |                                |
| :white_check_mark: | [amazonica+s3](./amazonica-s3)                       | Cloud API wrapper library                                                                         |                                |
| :white_check_mark: | [asami](./asami)                                     | Asami DB                                                                                          |                                |
| :white_check_mark: | [aws-api+s3](./aws-api-s3)                           | Cognitect AWS client library                                                                      |                                |
| :white_check_mark: | [buffy](./buffy)                                     | Buffy, The Byte Buffer Slayer                                                                     |                                |
| :white_check_mark: | [carmine](./carmine)                                 | Redis client and message queue for Clojure                                                        |                                |
| :white_check_mark: | [cheshire](./cheshire)                               | JSON parser/writer                                                                                |                                |
| :white_check_mark: | [cli4clj](./cli4clj)                                 | Interactive Command Line Interfaces (CLIs) for Clojure Applications                               |                                |
| :white_check_mark: | [cljfmt](./cljfmt)                                   | Source Formatter                                                                                  |                                |
| :white_check_mark: | [clj-http-lite](./clj-http-lite)                     | Web client                                                                                        |                                |
|        :x:         | [clj-sophia](./clj-sophia)                           | A fast RAM-Disk hybrid storage                                                                    | *Runtime error/JNA*            |
| :white_check_mark: | [clj-uuid](./clj-uuid)                               | RFC4122 Unique Identifiers for Clojure                                                            | No objects in namespaced uuids |
| :white_check_mark: | [clara-rules](./clara-rules)                         | A Clojure forward-chaining rules engine                                                           | *Using AOT compiled session*   |
| :white_check_mark: | [clostache](./clostache)                             | {{ mustache }} for Clojure                                                                        |                                |
| :white_check_mark: | [component](./component)                             | Managing lifecycle and dependencies of software                                                   |                                |
| :white_check_mark: | [cprop](./cprop)                                     | Configuration/property management                                                                 |                                |
| :white_check_mark: | [datascript](./datascript)                           | Immutable database and Datalog query engine                                                       |                                |
|     :warning:      | [fastmath](./fastmath)                               | Fast and primitive math and stats library                                                         | *See README*                   |
| :white_check_mark: | [fire](./fire)                                       | A lightweight clojure client for Firebase based using the REST API.                               |                                |
| :white_check_mark: | [hiccup](./hiccup)                                   | Fast library for rendering HTML in Clojure                                                        |                                |
| :white_check_mark: | [http-kit](./http-kit)                               | Web server and server                                                                             |                                |
| :white_check_mark: | [integrant](./integrant)                             | Alternative to mount, component etc.                                                              |                                |
| :white_check_mark: | [lacinia](./lacinia)                                 | A GraphQL server implementation in pure Clojure                                                   |                                |
| :white_check_mark: | [loom](./loom)                                       | A Graph manipulation and computation library.                                                     |                                |
|        :x:         | [monger](./monger)                                   | An idiomatic Clojure MongoDB driver with sane defaults                                            |                                |
| :white_check_mark: | [μ/log](./mulog)                                     | Event logging system                                                                              |                                |
| :white_check_mark: | [next.jdbc + honeysql](./next-jdbc)                  | Database driver and SQL-in-Clojure                                                                |                                |
| :white_check_mark: | [nippy](./nippy)                                     | Clojure serialization/deserialization library                                                     |                                |
| :white_check_mark: | [pp-grid](./pp-grid)                                 | A text-formatting library                                                                         |                                |
| :white_check_mark: | [ring/jetty](./ring-jetty)                           | Web server                                                                                        |                                |
| :white_check_mark: | [RoaringBitmap](./roaring)                           | Bitset library                                                                                    |                                |
| :white_check_mark: | [safely](./safely)                                   | Circuit breaker                                                                                   |                                |
| :white_check_mark: | [secure-random](./secure-random)                     | `SecureRandom` initialization                                                                     |                                |
| :white_check_mark: | [selmer](./selmer)                                   | A fast, Django inspired template system for Clojure.                                              |                                |
| :white_check_mark: | [system](./system)                                   | Layer on top of components                                                                        |                                |
| :white_check_mark: | [tech.ml.dataset](./tech.ml.dataset)                 | A Clojure high performance data processing system                                                 |                                |
| :white_check_mark: | [timbre](./timbre)                                   | Pure Clojure/Script logging library                                                               |                                |
| :white_check_mark: | [pedestal](./pedestal)                               | Pedestal is a sturdy and reliable base for services and APIs.                                     | requires reflect-config.json   |
| :white_check_mark: | [claypoole](./claypoole)                             | Claypoole: Threadpool tools for Clojure                                                           |                                |
| :white_check_mark: | [upit](./upit)                                       | Very very simple library to initialise your app stack.                                            |                                |
| :white_check_mark: | [zetasketch](./zetasketch)                           | Sketch data structures like HLL                                                                   | requires reflect-config.json   |
| :white_check_mark: | [pg2-core](./pg2-core)                               | A Fast PostgreSQL Driver For Clojure                                                              |                                |
| :white_check_mark: | [next.jdbc + SQLite Driver](./sqlite)                | A modern low-level Clojure wrapper for JDBC-based access to databases combined with SQLite Driver |                                |


More libraries to come (*PRs are welcome*).

Interesting GraalVM documentation to build native-images:

  - Understand [Class Initialization in Native Image](https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/ClassInitialization.md)
  - [Native Image Build Configuration](https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildConfiguration.md)
  - [URL Protocols in Native Image](https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/URLProtocols.md) for `http` and `https`
  - [JCA Security Services in Native Image](https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/JCASecurityServices.md)


## How to contribute

If you wish to add a library and contribute with a PR please follow these steps:

  1. Fork the project
  2. use the `sample-project` as a template. `cp -r sample-project my-library`
  3. add the library to the `project.clj` and the **native-image** parameters used
  4. amend the `my-library/src/simple/main.clj` to use the library
  5. amend the `my-library/README.md` with the info you discovered.
  6. Make a PR with your findings and "Thank you" in advance.

## License

Copyright © 2019-2021 Bruno Bonacci

Distributed under the Apache License v 2.0 (http://www.apache.org/licenses/LICENSE-2.0)
