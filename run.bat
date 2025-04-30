@echo off
REM Go to the root of the project
cd /d %~dp0

REM Create the bin directory if it doesn't exist
if not exist bin (
    mkdir bin
)

REM Compile all Java files inside src and its subfolders
javac -d bin -sourcepath src src\Main.java src\data\*.java src\logic\*.java src\ui\*.java

REM Run the main class
java -cp bin Main

pause
