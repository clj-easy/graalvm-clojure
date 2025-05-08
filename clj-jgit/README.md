# cheshire

Testing whether [clj-jgit](https://github.com/clj-jgit/clj-jgit) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [clj-jgit "1.1.0"]

Test with:

    lein do clean, uberjar, native, run-native
