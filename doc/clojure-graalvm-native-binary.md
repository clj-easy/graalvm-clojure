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

  :dependencies [[org.clojure/clojure "1.12.0"]
                 [com.github.clj-easy/graal-build-time "1.0.5"]]

  ;; add the main namespace
  :main hello-world.core

  ;; show reflection warnings.
  :global-vars {*warn-on-reflection* true}

  ;; add AOT compilation
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["do" ["clean"] ["uberjar"]
    ["shell"
     "native-image"
     ;;"--verbose"
     "--no-fallback"
     ;; if you are getting the following error, uncomment the next two lines
     ;; Error: Darwin native toolchain (x86_64) implies native-image target architecture class jdk.vm.ci.amd64.AMD64 but configured native-image target architecture is class jdk.vm.ci.aarch64.AArch64.
     ;;"--native-compiler-options=-arch"
     ;;"--native-compiler-options=arm64"
     "--features=clj_easy.graal_build_time.InitClojureClasses"
     "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
     "-o" "./target/${:name}"]]})
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

Now build a uberjar and the native image.

``` bash
$ lein native
Compiling hello-world.core
Created /private/tmp/hello-world/target/hello-world-0.1.0-SNAPSHOT.jar
Created /private/tmp/hello-world/target/hello-world-0.1.0-SNAPSHOT-standalone.jar

===========================================================================================
GraalVM Native Image: Generating 'hello-world' (executable)...
===========================================================================================
[clj-easy/graal-build-time] Registering packages for build time initialization: clojure, clj_easy.graal_build_time, hello_world
[1/8] Initializing...                                                       (5.5s @ 0.31GB)
 Java version: 25.0.2+10, vendor version: GraalVM CE 25.0.2+10.1
 Graal compiler: optimization level: 2, target machine: armv8.1-a
 C compiler: cc (apple, arm64, 17.0.0)
 Garbage collector: Serial GC (max heap size: 80% of RAM)
 2 user-specific feature(s):
 - clj_easy.graal_build_time.InitClojureClasses
 - com.oracle.svm.thirdparty.gson.GsonFeature
-------------------------------------------------------------------------------------------
Build resources:
 - 24.73GB of memory (36.0% of system memory, using available memory)
 - 10 thread(s) (100.0% of 10 available processor(s), determined at start)
[2/8] Performing analysis...  [******]                                      (4.8s @ 0.42GB)
    3,617 types,   3,966 fields, and  16,593 methods found reachable
    1,128 types,      36 fields, and     435 methods registered for reflection
       58 types,      58 fields, and      52 methods registered for JNI access
        0 downcalls and 0 upcalls registered for foreign access
        4 native libraries: -framework Foundation, dl, pthread, z
[3/8] Building universe...                                                  (0.9s @ 0.47GB)
[4/8] Parsing methods...      [*]                                           (0.5s @ 0.53GB)
[5/8] Inlining methods...     [***]                                         (0.4s @ 0.54GB)
[6/8] Compiling methods...    [***]                                         (5.4s @ 0.50GB)
[7/8] Laying out methods...   [*]                                           (1.1s @ 0.56GB)
[8/8] Creating image...       [*]                                           (1.3s @ 0.63GB)
   5.54MB (40.80%) for code area:     9,710 compilation units
   7.73MB (56.97%) for image heap:   93,960 objects and 55 resources
 301.56kB ( 2.22%) for other data
  13.57MB in total image size, 13.57MB in total file size
-------------------------------------------------------------------------------------------
Top 10 origins of code area:                 Top 10 object types in image heap:
   3.71MB java.base                             1.54MB byte[] for code metadata
 884.36kB svm.jar (Native Image)                1.23MB byte[] for java.lang.String
 496.02kB h.1.0-SNAPSHOT-standalone.jar       861.15kB java.lang.String
 105.87kB java.logging                        694.46kB c.o.s.core.hub.DynamicHubCompanion
  78.34kB org.graalvm.nativeimage.base        520.58kB java.lang.Class
  47.58kB jdk.proxy2                          461.26kB byte[] for general heap data
  38.69kB org.graalvm.nativeimage.configure   314.93kB java.util.HashMap$Node
  37.96kB jdk.proxy1                          230.68kB java.lang.Object[]
  25.66kB jdk.graal.compiler                  178.75kB java.util.HashMap$Node[]
  20.98kB org.graalvm.collections             163.34kB java.lang.String[]
  32.02kB for 6 more packages                   1.54MB for 1082 more object types
-------------------------------------------------------------------------------------------
Recommendations:
 FUTR: Use '--future-defaults=all' to prepare for future releases.
 HEAP: Set max heap for improved and more predictable memory usage.
 CPU:  Enable more CPU features with '-march=native' for improved performance.
-------------------------------------------------------------------------------------------
         0.9s (4.2% of total time) in 114 GCs | Peak RSS: 1.39GB | CPU load: 5.43
-------------------------------------------------------------------------------------------
Build artifacts:
 /private/tmp/hello-world/target/hello-world (executable)
===========================================================================================
Finished generating 'hello-world' in 20.6s.
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

### Multi-platform builds with GitHub Actions

If you a planning to build binaries for different platform like Linux, MacOS and Windows here is at
ready to use GitHub Action workflow to use.

But first update your `project.clj` to look as follow

``` clojure
(defproject hello-world "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.12.0"]
                 [com.github.clj-easy/graal-build-time "1.0.5"]]

  ;; add the main namespace
  :main hello-world.core

  ;; show reflection warnings.
  :global-vars {*warn-on-reflection* true}

  ;; add AOT compilation
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["do" ["clean"] ["uberjar"]
    ["shell"
     "native-image"
     ;;"--verbose"
     "--no-fallback"
     ;; if you are getting the following error, uncomment the next two lines
     ;; Error: Darwin native toolchain (x86_64) implies native-image target architecture class jdk.vm.ci.amd64.AMD64 but configured native-image target architecture is class jdk.vm.ci.aarch64.AArch64.
     ;;"--native-compiler-options=-arch"
     ;;"--native-compiler-options=arm64"
     "--features=clj_easy.graal_build_time.InitClojureClasses"
     "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
     "-o" "./target/${:name}"]]


   "native-win"
   ["do" ["clean"] ["uberjar"]
    ["shell"
     "native-image"
     ;;"--verbose"
     "--no-fallback"
     "--features=clj_easy.graal_build_time.InitClojureClasses"
     "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
     "-o" "./target/${:name}"]]
   }

  )
```


Finally add in your project the following file `.github/workflows/clojure-build-native.yml`

```
name: Clojure CI for cross-platform native images.

on: [push]


jobs:
  build:

    runs-on: ${{matrix.os}}
    strategy:
      matrix:
        include:
          - os: macos-latest
            name: macos
            arch: aarch64

          # https://github.com/graalvm/graalvm-ce-builds/releases/tag/jdk-25.0.1
          # Support for macOS x64 is deprecated. Version 25.0.1 is the last
          # release that supports this hardware architecture. In future,
          # GraalVM will only support macOS on AArch64 (Apple Silicon).
          # --------------------------------------------------------------------
          # - os: macos-15-intel
          #   name: macos
          #   arch: x64


          - os: ubuntu-latest
            name: linux
            arch: x64


    permissions:
      contents: read

    steps:
    - uses: actions/checkout@v4

    - name: Set up GraalVM
      uses: graalvm/setup-graalvm@v1.4.5
      with:
        java-version: '25'
        distribution: 'graalvm-community'
        native-image-job-reports: 'true'
        github-token: ${{ secrets.GITHUB_TOKEN }}

    - name: Install clojure tools
      uses: DeLaGuardo/setup-clojure@13.5
      with:
        # Install just one or all simultaneously
        # The value must indicate a particular version of the tool, or use 'latest'
        # to always provision the latest version
        cli: latest              # Clojure CLI based on tools.deps
        lein: latest             # Leiningen
        bb: latest               # Babashka
        cljfmt: latest           # cljfmt


    - name: Install dependencies (mac/linux)
      run: lein deps


    - name: Build all (mac/linux)
      run: lein compile :all


    - name: Run tests (mac/linux)
      run: lein test


    - name: Build Native (mac/linux)
      run: lein native


    - name: Archive binary
      uses: actions/upload-artifact@v4
      with:
        name: hello-world-${{ matrix.name }}-${{ matrix.arch }}
        path: target/hello-world


  build-windows:

    runs-on: ${{matrix.os}}
    strategy:
      matrix:
        include:
          - os: windows-latest
            name: windows
            arch: x64

    permissions:
      contents: read

    steps:
    - uses: actions/checkout@v4

    - name: Set up GraalVM
      uses: graalvm/setup-graalvm@v1.4.5
      with:
        java-version: '25'
        distribution: 'graalvm-community'
        native-image-job-reports: 'true'
        github-token: ${{ secrets.GITHUB_TOKEN }}

    - name: Install clojure tools
      uses: DeLaGuardo/setup-clojure@13.5
      with:
        # Install just one or all simultaneously
        # The value must indicate a particular version of the tool, or use 'latest'
        # to always provision the latest version
        cli: latest              # Clojure CLI based on tools.deps
        lein: latest             # Leiningen
        bb: latest               # Babashka
        cljfmt: latest           # cljfmt


    - name: Install dependencies (win)
      shell: cmd
      run: lein deps


    - name: Build all (win)
      shell: cmd
      run: lein compile :all


    - name: Run tests (win)
      shell: cmd
      run: lein test


    - name: Build Native (win)
      shell: cmd
      run: lein native-win


    - name: Archive binary
      uses: actions/upload-artifact@v4
      with:
        name: hello-world-${{ matrix.name }}-${{ matrix.arch }}
        path: target/hello-world.exe
```

Happy hacking!
