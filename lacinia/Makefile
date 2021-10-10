.PHONY: all test deps

PROJECT = lacinia
EXE = ./target/$(PROJECT)
JAR = ./target/simple-main.jar

all: test

test: $(EXE)
	lein run-native

deps: clean $(JAR)
	-java -jar -agentlib:native-image-agent=config-output-dir=clinit.d $(JAR)
	@echo Configuration files written to $$(pwd)/clinit.d

clean:
	-lein clean

$(JAR):
	@if [ ! -f $@ ]; then lein uberjar; fi

$(EXE): $(JAR)
	@if [ ! -f $@ ]; then lein native; fi
