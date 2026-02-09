#!/bin/bash

rm -rf classes lib
mkdir -p classes lib

javac -d classes -classpath .:classes src/Hello.java
cd classes
jar cf ../lib/Hello.jar Hello.class
cd ..

javac -d classes -classpath .:classes src/HelloImpl.java
cd classes
jar cf ../lib/HelloImpl.jar HelloImpl.class
cd ..

javac -d classes -cp .:classes:lib/Hello.jar:lib/HelloImpl.jar src/HelloServer.java
javac -d classes -cp .:classes:lib/Hello.jar src/HelloClient.java
export CLASSPATH=classes
