# upit

Use this project as template for testing a specific library with GraalVM **native-image**

library: https://github.com/sathyavijayan/upit

## Usage

Add to `project.clj`

    :repositories
    [["sats" {:url "https://maven.pkg.github.com/sathyavijayan/upit"
              :username :env/GH_PACKAGES_USR
              :password :env/GH_PACKAGES_PSW}]]

Currently testing:

    [sats/upit "0.0.1-SNAPSHOT"]


Test with:

    lein do clean, uberjar, native, run-native


Add any info might be useful for the reader.
