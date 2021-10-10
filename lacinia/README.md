# lacinia

This project generates a native binary image of a simple program using
[lacinia], a GraphQL server implementation in pure Clojure

## Usage

Configure with:

    make deps

Re-run this step after changing any project dependencies.

The `simple.schema` class reads files from disk, so `native-image` must
be run with the `-H:ResourceConfigurationFiles` option set to
`clinit.d/resource-config.json` (as `project.clj` should be doing already).

More information [here](https://www.graalvm.org/reference-manual/native-image/Resources).

Test with:

    make

The program will print the results of a simple GraphQL query.

## Results

_simple.main_
- `[com.walmartlabs.lacinia :as lacinia]` :white_check_mark:

_simple.schema_
- `[com.walmartlabs.lacinia.util :as util]` :white_check_mark:
- `[com.walmartlabs.lacinia.schema :as schema]` :white_check_mark:

## Notes

- **a GraalVM SDK from the [21.x or later series], targeting [Java 11 or later], is required**

- `clojure` 1.10.1 (and older) is affected by [this known issue];
   the minimum required version is 1.10.2

- some unidentified transitive dependency is calling into the Java UI
  widget libraries, so:

    + Linux targets will need the [FreeType 2 development package],
      in addition to the [required build dependencies][]

    + expect a fat binary (~70 Mb on a Linux desktop, or ~55 Mb on Windows)


[lacinia]: https://github.com/walmartlabs/lacinia
[this known issue]: https://github.com/oracle/graal/issues/1681
[required build dependencies]: https://www.graalvm.org/reference-manual/native-image/#prerequisites
[FreeType 2 development package]: https://pkgs.org/download/libfreetype6-dev
[21.x or later series]: https://github.com/oracle/graal/issues/2842#issuecomment-748628763
[Java 11 or later]: https://github.com/oracle/graal/issues/2842#issuecomment-802923557
