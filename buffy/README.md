# buffy

Testing whether [clojurewerkz/buffy](https://github.com/clojurewerkz/buffy)
library can be used in a native binary.

## Usage

Currently testing:
```clojure
[clojurewerkz/buffy "1.1.0"]
```

Test with:
```bash
lein do clean, uberjar, native, run-native
```
