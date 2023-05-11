# Sample-project

Use this project as template for testing a specific library with GraalVM **native-image**

## Usage

Currently testing:

    [hickory "0.7.1]

Test with:

    lein do clean, uberjar, native, run-native

1. clj-easy/graal-build-time does not work here! Instead you need to rely on the deprecated `--initialize-at-build-time`
2. hickory 0.7.2 and 0.7.3 do not compile with the demo project provided as is (note this is fixed in master)
3. Note that `native-image` was not in my path, so I edited the project.clj to hardcode it's full path.
4. For a slightly more complex project combining hickory with clj-http-lite check out [bbyt](https://github.com/port19x/bbyt).
5. Expect build times of 1-2 minutes, that's normal.
6. Test out different permutations of cli args for native-image. I have found `"-J-Xmx3g"` to be unnecessary for example.
