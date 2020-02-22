# secure-random

The smallest project possible, which demonstrates the issue with
`SecureRandom` objects bound to dynamic Vars.

`SecureRandom` class need to be initialized at Runtime.

## Usage

 Run `lein do clean, uberjar, native, run-native`
