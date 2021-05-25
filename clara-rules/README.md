# Sample-project

Use this project as template for testing a specific library with GraalVM **native-image**

## Usage

Currently testing:

```clojure
    [com.cerner/clara-rules "0.21.0]
```

Test with:

```bash
$ lein do clean, uberjar, native, run-native
Compiling simple.main
Compiling simple.rules
Created /Users/jgomez/code/graalvm-clojure/clara-rules/target/clara-rules-0.1.0-SNAPSHOT.jar
Created /Users/jgomez/code/graalvm-clojure/clara-rules/target/simple-main.jar
[./target/clara-rules:35966]    classlist:   1,250.43 ms,  1.69 GB
[./target/clara-rules:35966]        (cap):   2,376.34 ms,  1.69 GB
[./target/clara-rules:35966]        setup:   3,730.14 ms,  1.69 GB
[./target/clara-rules:35966]     (clinit):     223.96 ms,  1.83 GB
[./target/clara-rules:35966]   (typeflow):   5,531.08 ms,  1.83 GB
[./target/clara-rules:35966]    (objects):   4,303.29 ms,  1.83 GB
[./target/clara-rules:35966]   (features):     199.42 ms,  1.83 GB
[./target/clara-rules:35966]     analysis:  10,594.68 ms,  1.83 GB
[./target/clara-rules:35966]     universe:     752.38 ms,  1.90 GB
[./target/clara-rules:35966]      (parse):   1,214.02 ms,  1.91 GB
[./target/clara-rules:35966]     (inline):   1,501.52 ms,  1.99 GB
[./target/clara-rules:35966]    (compile):   7,851.20 ms,  3.08 GB
[./target/clara-rules:35966]      compile:  11,150.27 ms,  3.08 GB
[./target/clara-rules:35966]        image:   1,222.61 ms,  3.10 GB
[./target/clara-rules:35966]        write:     443.18 ms,  3.10 GB
[./target/clara-rules:35966]      [total]:  29,328.73 ms,  3.10 GB
High support requested!
Notify Alice that Acme has a new support request!
```

Additionally:

Native image compilation works with GraalVM-CE-Java11-20.1.0 and GraalVM-CE-Java8-20.0.0.
