# amazonica-s3

Testing whether [Amazonica/s3](https://github.com/mcohen01/amazonica) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [amazonica "0.3.152"]

Test with:

    lein do clean, uberjar, native-config, native, run-native

To be able to run this need to provide AWS credentials:

    export AWS_ACCESS_KEY_ID=xxx
    export AWS_SECRET_ACCESS_KEY=yyyy
    export AWS_DEFAULT_REGION=eu-west-1

Building this image requires a more complex procedure.  After building
the uberjar as usual we will run the application with a java-agent
that will capture several parameters such as reflected classes, proxy
instances, and jni config.

It is good idea to cover as much as possible of the run-time
functionality during this step, the more information will be able to
collect, the easier/better will be the compilation step.

``` bash
java -agentlib:native-image-agent=config-output-dir=./target/config/ \
    -jar ./target/amazonica-s3-0.1.0-SNAPSHOT.jar
```

If multiple execution are required there are ways to merge the
generated config.

We will then feed this configuration into the native image compilation
by adding the following flag:

``` bash
# addint the following flag to native-image
# will tell the compiler to use the information we
# extracted from the introspection step

  -H:ConfigurationFileDirectories=./target/config/
```

**IMPORTANT**: Finally, I've noticed that after the compilation, the
native image was still using the AWS configuration I had at set during
the compilation.  So even after changing the account credentials it
was still connecting to the same account I used during the
compilation.  To avoid this, I had to add the following flag to force
the initialization of the credential provider to happen at runtime.

``` bash
--initialize-at-run-time=com.amazonaws.auth.DefaultAWSCredentialsProviderChain
```

After rebuilding, everything worked fine.!
