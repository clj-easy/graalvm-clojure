@echo off
@rem first, generate dependency info:
@rem  make deps
@rem . . . then build and run:
@rem  make
SETLOCAL EnableExtensions
SET "JAR=target\simple-main.jar"

IF "%1" == "clean" (
  lein clean
  GOTO END
)

IF "%1" == "deps" (
  lein do clean, uberjar
  java -jar -agentlib:native-image-agent=config-output-dir=clinit.d "%JAR%"
  echo. Configuration files written to %CD%/clinit.d
) ELSE (
  lein do uberjar, native, run-native
)

:END
ENDLOCAL
