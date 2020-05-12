# clj-uuid

Testing whether [clj-uuid](https://github.com/danlentz/clj-uuid) library can be used in a native binary image with GraalVM.

## Usage

Currently testing:

    [clj-uuid "0.1.9"]

Test with:

    lein do clean, uberjar, native, run-native

## Results
`[clj-uuid]` :white_check_mark:

## Notes
`(uuid/v3)` and `(uuid/v5)` with objects as the local constituent will fail. 

```clojure
(uuid/v3 uuid/+namespace-url+ "I am clearly not a URL") => works!
(uuid/v5 uuid/+namespace-url+ "I am clearly not a URL") => works!

(uuid/v3 uuid/+namespace-url+ :keyword) => fails!
(uuid/v3 uuid/+namespace-url+ 'this-symbol) => fails!
(uuid/v5 uuid/+namespace-url+ :keyword) => fails!
(uuid/v5 uuid/+namespace-url+ 'this-symbol) => fails!
```

```bash
Exception in thread "main" com.oracle.svm.core.jdk.UnsupportedFeatureError: ObjectOutputStream.writeObject()
```

This is because GraalVM currently doesn't support Java serialization, as seen issue [460](https://github.com/oracle/graal/issues/460). [PR 2323](https://github.com/oracle/graal/pull/2323) attempts to address this issue.   
"Until the time comes. The way is shut." 