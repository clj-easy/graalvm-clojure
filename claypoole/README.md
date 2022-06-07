# claypoole

Testing whether [clj-commons/claypoole](https://github.com/clj-commons/claypoole)
library can be used in a native binary.

## Usage

Currently testing:
```clojure
[org.clj-commons/claypoole "1.2.2"]
```

Test with:
```bash
lein do clean, uberjar, native, run-native
```
