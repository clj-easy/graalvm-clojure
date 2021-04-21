# carmine

Project to test if [carmine](https://github.com/ptaoussanis/carmine) can be used in a native binary image with GraalVM.

## GraalVM Version
```
openjdk version "11.0.10" 2021-01-19
OpenJDK Runtime Environment GraalVM CE 21.0.0.2 (build 11.0.10+8-jvmci-21.0-b06)
OpenJDK 64-Bit Server VM GraalVM CE 21.0.0.2 (build 11.0.10+8-jvmci-21.0-b06, mixed mode, sharing)
```

## Usage

Currently testing:

    [com.taoensso/carmine "3.1.0"]

Test with:

    lein do clean, uberjar, native, run-native

Building a native image that contains carmine in it requires some extra work. Since carmine uses dynamic class loading to load `org.apache.commons.pool2.impl.EvictionPolicy`, so we'll need to supply a reflection configuration file to GraalVM in order for it to load the class at runtime.

Add this to a file named `reflect-config.json` and add it to your GraalVM configuration directory:
```
[
  {
    "name":"org.apache.commons.pool2.impl.DefaultEvictionPolicy",
    "allPublicConstructors" : true,
  }
]
```
and when building the native image use:

`native-image -H:ConfigurationFileDirectories=./path/to/config/dir`