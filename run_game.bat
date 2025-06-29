@echo off
cd /d "%~dp0"
javac -cp ".;mysql-connector-java-8.0.30.jar" *.java
java  -cp ".;mysql-connector-java-8.0.30.jar" Main
pause
