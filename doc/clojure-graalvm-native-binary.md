# How to build a native binary for your Clojure projects with GraalVM

This guide shows the steps required to build native binaries out of
your Clojure projects using GraalVM.


### Step1 - Download and install GraalVM
Go to https://github.com/graalvm/graalvm-ce-builds/releases and download the
binaries for your platform.

Unpack the package in a folder and add it to the path:

``` bash
$ export GRAALVM_HOME=/full/path/to/graalvm
$ export PATH=$GRAALVM_HOME/bin:$PATH
$ java -version
openjdk version "11.0.7" 2020-04-14
OpenJDK Runtime Environment GraalVM CE 20.1.0 (build 11.0.7+10-jvmci-20.1-b02)
OpenJDK 64-Bit Server VM GraalVM CE 20.1.0 (build 11.0.7+10-jvmci-20.1-b02, mixed mode, sharing)
```

Now install the `native-image` component:

``` bash
$ gu install native-image
Downloading: Component catalog from www.graalvm.org
Processing Component: Native Image
Downloading: Component native-image: Native Image  from github.com
Installing new component: Native Image (org.graalvm.native-image, version 20.1.0)

$ gu list
ComponentId              Version             Component name      Origin
--------------------------------------------------------------------------------
graalvm                  20.1.0              GraalVM Core
native-image             20.1.0              Native Image        github.com
```

**NOTE**: *if you are on Mac OSX you might need to de-quarantine the binaries.*
Here a script to do so:

``` bash
# for Mac OSX
sudo xattr -r -d com.apple.quarantine ${GRAALVM_HOME}
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
  ;; clojure version "1.10.2-alpha1" includes fixes for some graalvm specific issues
  ;; see https://clojure.org/community/devchangelog#_release_1_10_2
  :dependencies [[org.clojure/clojure "1.10.3"]]
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
Created /private/tmp/hello-world/target/hello-world-0.1.0-SNAPSHOT.jar
Created /private/tmp/hello-world/target/hello-world-0.1.0-SNAPSHOT-standalone.jar
```

Now that you have a `-standalone.jar` file which contains all the
classes of your projects and all the dependencies all in one jar, you
can proceed to build the native binary.

**NOTE:** If you are running with a earlier version than *GraalVM v19.0.0*
          you don't need the flag `--initialize-at-build-time`.

``` bash
native-image --report-unsupported-elements-at-runtime \
             --initialize-at-build-time \
             --no-server \
             -jar ./target/hello-world-0.1.0-SNAPSHOT-standalone.jar \
             -H:Name=./target/hello-world

[./target/hello-world:33840]    classlist:   3,119.60 ms,  0.96 GB
[./target/hello-world:33840]        (cap):   2,250.97 ms,  0.96 GB
[./target/hello-world:33840]        setup:   3,980.23 ms,  0.96 GB
[./target/hello-world:33840]     (clinit):     163.43 ms,  1.72 GB
[./target/hello-world:33840]   (typeflow):   6,249.38 ms,  1.72 GB
[./target/hello-world:33840]    (objects):   4,975.02 ms,  1.72 GB
[./target/hello-world:33840]   (features):     202.49 ms,  1.72 GB
[./target/hello-world:33840]     analysis:  11,819.61 ms,  1.72 GB
[./target/hello-world:33840]     universe:     341.69 ms,  1.72 GB
[./target/hello-world:33840]      (parse):   1,850.44 ms,  1.72 GB
[./target/hello-world:33840]     (inline):   2,497.03 ms,  1.72 GB
[./target/hello-world:33840]    (compile):  12,415.94 ms,  2.35 GB
[./target/hello-world:33840]      compile:  17,341.60 ms,  2.35 GB
[./target/hello-world:33840]        image:   1,197.96 ms,  2.35 GB
[./target/hello-world:33840]        write:     643.75 ms,  2.35 GB
[./target/hello-world:33840]      [total]:  38,716.97 ms,  2.35 GB
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
    "--initialize-at-build-time" "--no-server"
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
    "--initialize-at-build-time" "--no-server"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]}
  )
```

With this in place you can just run `lein native` to build the native binary:

``` bash
$ lein native
OpenJDK 64-Bit Server VM warning: forcing TieredStopAtLevel to full optimization because JVMCI is enabled
[./target/hello-world:33980]    classlist:   2,970.75 ms,  0.96 GB
[./target/hello-world:33980]        (cap):   2,824.32 ms,  0.96 GB
[./target/hello-world:33980]        setup:   4,532.29 ms,  0.96 GB
[./target/hello-world:33980]     (clinit):     180.49 ms,  1.72 GB
[./target/hello-world:33980]   (typeflow):   6,960.70 ms,  1.72 GB
[./target/hello-world:33980]    (objects):   4,050.59 ms,  1.72 GB
[./target/hello-world:33980]   (features):     267.73 ms,  1.72 GB
[./target/hello-world:33980]     analysis:  11,822.33 ms,  1.72 GB
[./target/hello-world:33980]     universe:     322.57 ms,  1.72 GB
[./target/hello-world:33980]      (parse):   1,758.44 ms,  1.72 GB
[./target/hello-world:33980]     (inline):   2,497.64 ms,  1.72 GB
[./target/hello-world:33980]    (compile):  12,186.63 ms,  2.35 GB
[./target/hello-world:33980]      compile:  17,039.18 ms,  2.35 GB
[./target/hello-world:33980]        image:   1,252.06 ms,  2.35 GB
[./target/hello-world:33980]        write:     430.08 ms,  2.35 GB
[./target/hello-world:33980]      [total]:  38,668.99 ms,  2.35 GB

$ ./target/hello-world
Hello, World!
```

Happy hacking!
