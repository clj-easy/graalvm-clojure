# next.jdbc + honeysql

Testing whether [next.jdbc](https://github.com/seancorfield/next-jdbc) and [honeysql](https://github.com/jkk/honeysql) can be used with PostgreSQL in a native binary image with GraalVM.

## Usage

Currently testing:

    [seancorfield/next.jdbc "1.0.409"]
    [org.postgresql/postgresql "42.2.11"]
    [honeysql "0.9.10"]

Test with:

    lein do clean, uberjar, native, run-native [host] [db] [user] [password]
    e.g. lein do clean, uberjar, native, run-native db.server.com graal graaltester p@55w0rd


## Results
`[next.jdbc :as jdbc]` :white_check_mark:   
`[honeysql.core :as sql]` :white_check_mark: 
`[sqlingvo.core :as sql]` :x:


## Notes
`sqlingvo` contains reflection in it's core code and so doesn't work. It would have been the ideal sql library as it supports table creation. 
