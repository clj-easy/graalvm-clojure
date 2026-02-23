# AMD64
FROM ghcr.io/graalvm/native-image:22.3.3@sha256:45af9f40460aba188d27ff3ce9ef56765965af8f6fbcb4c672d7897f2d4b5775
# ARM64
#FROM ghcr.io/graalvm/native-image:22.3.3@sha256:48837ac77ec827dbb76a8fc8c2e82806f180bb68de9039df3af0b5f335c5a7ad

# install leiningen
RUN curl -o /usr/local/bin/lein https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
RUN chmod a+x /usr/local/bin/lein

# create working directory
RUN mkdir native
WORKDIR /native

# copy project files
COPY . /native

# build uberjar
RUN lein do clean, uberjar

# create native image
RUN lein native

# run normal
RUN lein run
# run native
RUN lein run-native