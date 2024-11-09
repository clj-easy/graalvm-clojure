# PG2

Testing whether [pg2](https://github.com/igrishaev/pg2) library can be used in a native binary image with GraalVM(23).

## Usage

Currently testing:

    [com.github.igrishaev/pg2-core "0.1.19"]

Test with (requires a local GraalVM installation):

- You need to start the PostgreSQL container before executing the bynary result: `docker compose up -d`

- Generating and executing the image: ```lein do clean, uberjar, native, run-native```

## Results

`[pg.core :as pg]` :white_check_mark:
`[pg.pool :as pool]` :white_check_mark:
