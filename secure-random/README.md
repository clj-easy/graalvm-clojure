# secure-random

The smallest project possible, which demonstrates the issue with
`SecureRandom` objects bound to dynamic Vars.

`SecureRandom` class need to be initialized at Runtime.

## Usage

1. Point `[:native-image :graal-bin]` in project.clj to your GRAAL_VM installation.
2. Run `lein native-image`
3. the run `./target/simple-main`
