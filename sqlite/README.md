# next.jdbc + SQLite Driver

Testing
whether [next.jdbc](https://github.com/seancorfield/next-jdbc) + [SQLite driver](https://github.com/xerial/sqlite-jdbc)
can be used in a native binary image with GraalVM(23).

## Usage

Currently testing:

    [org.xerial/sqlite-jdbc "3.41.2.1"]
    [com.github.seancorfield/next.jdbc "1.3.955"]

Test with (requires a local GraalVM installation):

    lein do clean, uberjar, native, run-native

## Results

    [clojure.java.io :as io] :white_check_mark:

    [next.jdbc :as jdbc] :white_check_mark:
    
    [next.jdbc.result-set :as rs] :white_check_mark:
