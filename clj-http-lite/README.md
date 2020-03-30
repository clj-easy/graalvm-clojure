# clj-http-lite

Testing whether [clj-http-lite](https://github.com/martinklepsch/clj-http-lite) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [org.martinklepsch/clj-http-lite "0.4.3"]

Test with:

    Start an nginx server in the terminal using `docker-compose up`

    Then in a new terminal: `lein do clean, uberjar, native, run-native`

## Results
`[clj-http.lite.client :as client]` :white_check_mark:   

