@echo off
cd /d "%~dp0"
if not exist out mkdir out
javac -encoding UTF-8 -Xlint:all -d out src\passwordcheck\*.java test\passwordcheck\*.java
if errorlevel 1 exit /b 1
java -cp out passwordcheck.PasswordAnalyzerTest
