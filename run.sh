#!/bin/bash
# Navigate to the root of the project
cd "$(dirname "$0")"

# Create the bin directory if it doesn't exist
if [ ! -d "bin" ]; then
    mkdir bin
fi

# Compile all Java files inside src and its subfolders
javac -d bin -sourcepath src src/Main.java src/data/*.java src/logic/*.java src/ui/*.java

# Run the main class
java -cp bin Main
