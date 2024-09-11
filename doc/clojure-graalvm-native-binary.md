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

For GraalVM versions before v21, you must now install the `native-image` component:

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

At this point you should have access to the `native-image` command:

```
$ native-image
Please specify options for native-image building or use --help for more info.
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
  :dependencies [[org.clojure/clojure "1.12.0"]]
  ;; add the main namespace
  :main hello-world.core

  ;; add AOT compilation
  :profiles {:uberjar {:aot :all}})
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

Warning: Ignoring server-mode native-image argument --no-server.
Warning: The option '-H:Name=./target/hello-world' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: Please re-evaluate whether any experimental option is required, and either remove or unlock it. The build output lists all active experimental options, including where they come from and possible alternatives. If you think an experimental option should be considered as stable, please file an issue.
========================================================================================================================
GraalVM Native Image: Generating 'hello-world' (executable)...
========================================================================================================================
For detailed information and explanations on the build output, visit:
https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md
------------------------------------------------------------------------------------------------------------------------
[1/8] Initializing...                                                                                    (8.1s @ 0.14GB)
 Java version: 21.0.2+13, vendor version: GraalVM CE 21.0.2+13.1
 Graal compiler: optimization level: 2, target machine: x86-64-v3
 C compiler: cc (apple, x86_64, 15.0.0)
 Garbage collector: Serial GC (max heap size: 80% of RAM)
 1 user-specific feature(s):
 - com.oracle.svm.thirdparty.gson.GsonFeature
------------------------------------------------------------------------------------------------------------------------
 1 experimental option(s) unlocked:
 - '-H:Name' (alternative API option(s): -o hello-world; origin(s): command line)
------------------------------------------------------------------------------------------------------------------------
Build resources:
 - 10.44GB of memory (32.6% of 32.00GB system memory, determined at start)
 - 8 thread(s) (100.0% of 8 available processor(s), determined at start)
[2/8] Performing analysis...  [*****]                                                                   (15.4s @ 0.31GB)
    3,582 reachable types   (73.9% of    4,850 total)
    4,056 reachable fields  (49.9% of    8,130 total)
   17,154 reachable methods (45.5% of   37,736 total)
    1,097 types,    87 fields, and   695 methods registered for reflection
       57 types,    57 fields, and    52 methods registered for JNI access
        4 native libraries: -framework Foundation, dl, pthread, z
[3/8] Building universe...                                                                               (3.4s @ 0.33GB)
[4/8] Parsing methods...      [*]                                                                        (1.9s @ 0.38GB)
[5/8] Inlining methods...     [***]                                                                      (1.7s @ 0.38GB)
[6/8] Compiling methods...    [****]                                                                    (16.0s @ 0.32GB)
[7/8] Layouting methods...    [*]                                                                        (2.0s @ 0.38GB)
[8/8] Creating image...       [**]                                                                       (2.9s @ 0.30GB)
   6.01MB (41.80%) for code area:    10,000 compilation units
   8.16MB (56.77%) for image heap:  102,439 objects and 47 resources
 210.63kB ( 1.43%) for other data
  14.38MB in total
------------------------------------------------------------------------------------------------------------------------
Top 10 origins of code area:                                Top 10 object types in image heap:
   4.11MB java.base                                            1.80MB byte[] for code metadata
 976.71kB svm.jar (Native Image)                               1.33MB byte[] for java.lang.String
 550.00kB hello-world-0.1.0-SNAPSHOT-standalone.jar         1012.91kB java.lang.String
 113.72kB java.logging                                       880.58kB java.lang.Class
  65.03kB org.graalvm.nativeimage.base                       307.83kB com.oracle.svm.core.hub.DynamicHubCompanion
  47.59kB jdk.proxy1                                         279.30kB byte[] for general heap data
  45.84kB jdk.proxy3                                         244.73kB java.util.HashMap$Node
  27.06kB jdk.internal.vm.ci                                 225.45kB java.lang.Object[]
  22.06kB org.graalvm.collections                            210.27kB heap alignment
  11.42kB jdk.proxy2                                         193.56kB java.lang.String[]
  11.17kB for 3 more packages                                  1.75MB for 1035 more object types
------------------------------------------------------------------------------------------------------------------------
Recommendations:
 INIT: Adopt '--strict-image-heap' to prepare for the next GraalVM release.
 HEAP: Set max heap for improved and more predictable memory usage.
 CPU:  Enable more CPU features with '-march=native' for improved performance.
------------------------------------------------------------------------------------------------------------------------
                        2.9s (5.5% of total time) in 287 GCs | Peak RSS: 0.95GB | CPU load: 5.01
------------------------------------------------------------------------------------------------------------------------
Produced artifacts:
 /Users/clojure/hello-world/target/hello-world (executable)
========================================================================================================================
Finished generating 'hello-world' in 52.0s.
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
  :dependencies [[org.clojure/clojure "1.12.0"]]
  :main hello-world.core
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime"
    "--initialize-at-build-time" "--no-server"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]})
```

With this in place you can just run `lein native` to build the native binary:

``` bash
$ lein native
...
Produced artifacts:
 /Users/clojure/hello-world/target/hello-world (executable)
========================================================================================================================
Finished generating 'hello-world' in 52.4s.

$ ./target/hello-world
Hello, World!
```

Happy hacking!
