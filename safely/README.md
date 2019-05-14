# safely

Testing whether [Safely](https://github.com/BrunoBonacci/safely) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [com.brunobonacci/safely "0.5.0-alpha6"]

Test with:

    lein do clean, uberjar, native, run-native
