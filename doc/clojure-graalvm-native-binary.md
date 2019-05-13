# How to build a native binary for your Clojure projects with GraalVM

This guide shows the steps required to build native binaries out of
your Clojure projects using GraalVM.


### Step1 - Download and install GraalVM
Go to https://github.com/oracle/graal/releases and download the
binaries for your platform.

Unpack the package in a folder and add it to the path:

``` bash
$ export GRAALVM_HOME=/full/path/to/graalvm
$ export PATH=$GRAALVM_HOME/bin:$PATH
$ java -version
openjdk version "1.8.0_212"
OpenJDK Runtime Environment (build 1.8.0_212-20190420112649.buildslave.jdk8u-src-tar--b03)
OpenJDK GraalVM CE 19.0.0 (build 25.212-b03-jvmci-19-b01, mixed mode)
```

Now install the `native-image` component:

``` bash
$ gu install native-image
Downloading: Component catalog from www.graalvm.org
Processing component archive: Native Image
Component Native Image (org.graalvm.native-image) installed.

$ gu list
ComponentId              Version             Component name      Origin
--------------------------------------------------------------------------------
graalvm                  19.0.0              GraalVM Core
native-image             19.0.0              Native Image licencegithub.com
```

### Step2 - Your project

Create a project:

``` bash
$ lein new hello-world
Generating a project called hello-world based on the 'default' template.
The default template is intended for library projects, not applications.
To see other templates (app, plugin, etc), try `lein help new`.

$ cd hello-world/
```

Update the `project.clj` and add the `:main`

``` clojure
(defproject hello-world "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  ;; add the main namespace
  :main hello-world.core

  ;; add AOT compilation
  :profiles {:uberjar {:aot :all}}
  )
```

Add a `-main` function in your `hello-world.core` namespace

``` clojure
(ns hello-world.core
  (:gen-class))

(defn -main
  [& args]
  (println "Hello, World!"))
```

Test it!

``` bash
$ lein run
Hello, World!
```

### Step3 - build the native binary

Now build a uberjar.

``` bash
$ lein do clean, uberjar
Compiling hello-world.core
Created /private/tmp/5/hello-world/target/hello-world-0.1.0-SNAPSHOT.jar
Created /private/tmp/5/hello-world/target/hello-world-0.1.0-SNAPSHOT-standalone.jar
```

Now that you have a `-standalone.jar` file which contains all the
classes of your projects and all the dependencies all in one jar, you
can proceed to build the native binary.

**NOTE:** If you are running with a earlier version than *GraalVM v19.0.0*
          you don't need the flag `--initialize-at-build-time`.

``` bash
native-image --report-unsupported-elements-at-runtime \
             --initialize-at-build-time \
             -jar ./target/hello-world-0.1.0-SNAPSHOT-standalone.jar \
             -H:Name=./target/hello-world

Build on Server(pid: 76573, port: 63429)*
[./target/hello-world:76573]    classlist:   2,931.41 ms
[./target/hello-world:76573]        (cap):   2,494.11 ms
[./target/hello-world:76573]        setup:   4,030.12 ms
[./target/hello-world:76573]   (typeflow):   5,544.43 ms
[./target/hello-world:76573]    (objects):   2,177.19 ms
[./target/hello-world:76573]   (features):     225.10 ms
[./target/hello-world:76573]     analysis:   8,098.25 ms
[./target/hello-world:76573]     (clinit):     136.48 ms
[./target/hello-world:76573]     universe:     399.00 ms
[./target/hello-world:76573]      (parse):   1,106.76 ms
[./target/hello-world:76573]     (inline):   1,968.59 ms
[./target/hello-world:76573]    (compile):   9,156.00 ms
[./target/hello-world:76573]      compile:  12,728.30 ms
[./target/hello-world:76573]        image:     975.56 ms
[./target/hello-world:76573]        write:     252.32 ms
[./target/hello-world:76573]      [total]:  29,587.34 ms
```

That's it! now you can test your native binary!

``` bash
$ ./target/hello-world
Hello, World!
```

Check the speed difference!

``` bash
$ time java -jar ./target/hello-world-0.1.0-SNAPSHOT-standalone.jar
Hello, World!

real    0m1.008s
user    0m1.795s
sys     0m0.190s


$ time ./target/hello-world
Hello, World!

real    0m0.010s
user    0m0.004s
sys     0m0.004s
```

### A final touch

If you don't want to remember the command line to build the native binary you can always
add it to the `project.clj` as follow:

Add the [`lein-shell` plugin](https://github.com/hypirion/lein-shell) to the `project.clj`

``` clojure
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}
```

Now you can add an alias for it the `project.clj itself:

``` clojure
  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime"
    "--initialize-at-build-time"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]}
```

Overall your `project.clj` should look like as follow:

``` clojure
(defproject hello-world "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main hello-world.core
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime"
    "--initialize-at-build-time"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]}
  )
```

With this in place you can just run `lein native` to build the native binary:

``` bash
$ lein native
Build on Server(pid: 76573, port: 63429)
[./target/hello-world:76573]    classlist:     925.69 ms
[./target/hello-world:76573]        (cap):   1,052.47 ms
[./target/hello-world:76573]        setup:   1,416.62 ms
[./target/hello-world:76573]   (typeflow):   3,499.51 ms
[./target/hello-world:76573]    (objects):   1,224.39 ms
[./target/hello-world:76573]   (features):     129.42 ms
[./target/hello-world:76573]     analysis:   4,946.87 ms
[./target/hello-world:76573]     (clinit):      95.94 ms
[./target/hello-world:76573]     universe:     298.63 ms
[./target/hello-world:76573]      (parse):     622.46 ms
[./target/hello-world:76573]     (inline):   1,119.10 ms
[./target/hello-world:76573]    (compile):   4,646.36 ms
[./target/hello-world:76573]      compile:   6,730.73 ms
[./target/hello-world:76573]        image:     672.79 ms
[./target/hello-world:76573]        write:     199.98 ms
[./target/hello-world:76573]      [total]:  15,268.99 ms

$ ./target/hello-world
Hello, World!
```
