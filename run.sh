#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p out
javac -encoding UTF-8 -d out src/passwordcheck/*.java
java -cp out passwordcheck.psswrdcheck
