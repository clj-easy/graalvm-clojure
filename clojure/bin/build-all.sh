#!/bin/bash

if [ ! -e graal.paths ] ; then
    echo 'Missing graal.paths file.'
    echo 'Please provide the list of GraalVM home directory in a file called graal.paths'
    exit 1
fi

OUT=$(dirname $0)/../out
rm -fr $OUT
mkdir -p $OUT

function banner(){
    echo ''
    echo ''
    echo '----------------------------------------------------------------------'
    echo '                      '$1
    echo '----------------------------------------------------------------------'
}

function build-jar(){
    echo '(-) Building' $1 'uberjar'
    lein with-profile $1 do clean, uberjar
    cp ./target/simple-$1-uberjar.jar $(dirname $0)/../out/
}

function build-native(){
    GRAAL_VER=$($2/bin/java -version 2>&1 | grep GraalVM | cut -d' ' -f4)
    echo '(-) Building' $1 'native with GraalVM' $GRAAL_VER
    $2/bin/native-image $NATIVE_OPTS --report-unsupported-elements-at-runtime -jar ./out/simple-$1-uberjar.jar -H:Name=./out/simple-$1-graal-$GRAAL_VER
}

#
# Clojure versions to try out
#
clj_versions=(clojure-1.10.0 clojure-1.9.0 clojure-1.8.0 clojure-1.7.0)

#
# GraalVM version to try out (GRAAL home paths)
#
graal_versions=$(cat graal.paths)

#
# Build all the uberjars and all the native images
#
# add native-image options here:
NATIVE_OPTS=""

for c in "${clj_versions[@]}" ; do
    banner "Building $c"
    build-jar $c
    for g in $graal_versions ; do
        build-native $c $g
    done
done


#
# all the artifacts
#

banner 'Verifying native images'
for g in $graal_versions ; do
    gv=$($g/bin/java -version 2>&1 | grep GraalVM | cut -d' ' -f4)
    for c in "${clj_versions[@]}" ; do
        p=simple-$c-graal-$gv
        if [ -e $OUT/$p ] ; then
            test_p=`$OUT/$p &> /dev/null && echo OK || echo FAIL`
        else
            test_p='ERROR'
        fi
        printf "verifying %-40s : %s\n" $p $test_p
    done
done

# END
