#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p out
javac -encoding UTF-8 -Xlint:all -d out src/passwordcheck/*.java test/passwordcheck/*.java
java -cp out passwordcheck.PasswordAnalyzerTest
