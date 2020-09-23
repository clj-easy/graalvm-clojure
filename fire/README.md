# fire

Testing whether [fire](https://github.com/alekcz/fire) can be used to
read and write to Firebase's realtime database in a native binary
image with GraalVM.

## Usage

Currently testing:

    [alekcz/fire "0.3.0]

Test with:

    docker-compose up -d
    lein do clean, uberjar, native, run-native
    docker-compose down

## Results

`[fire.core :as fire]` :white_check_mark:
`[fire.auth :as auth]` :white_check_mark:
