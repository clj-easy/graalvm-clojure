# cli4clj

cli4clj is a library that aims on enabling developers to "Create simple interactive CLIs for Clojure applications.": https://github.com/ruedigergad/cli4clj

## Usage

Currently testing:

    [cli4clj "1.7.9"]

Test with:

    lein do clean, uberjar, native, run-native

Command line options:

- Run without command line options for the "classic" or "old" interactive command line interface look and feel.
- Append "alt" to the command line options for the "classic" or "old" interactive command line interface look and feel.
  - E.g.: lein run-native -- alt

