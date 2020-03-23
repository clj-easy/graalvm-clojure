# next.jdbc + sqlingvo

Testing whether [next.jdbc](https://github.com/seancorfield/next-jdbc) can be used for database connections in a native binary image with GraalVM.

## Usage

Currently testing:

    [seancorfield/next.jdbc "1.0.409"]
    [org.postgresql/postgresql "42.2.11"]

Test with:

    lein do clean, uberjar, native, run-native [host] [db] [user] [password]


## Results
`[next.jdbc :as jdbc]` :white_check_mark:   
`[sqlingvo.core :as sql]` :x:


## Notes
`sqlingvo` contains reflection in it's core code and so doesn't work.
```

```