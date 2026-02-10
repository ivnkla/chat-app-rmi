#!/bin/bash

echo "> cleaning up"
rm -rf classes lib
mkdir -p classes lib

echo "> compiling message object files"
javac -d classes -classpath .:classes src/Message.java
cd classes
jar cf ../lib/Message.jar Message.class
cd ..

echo "> compiling client interface"
javac -d classes -classpath .:classes src/ClientEndpoint.java
cd classes
jar cf ../lib/ClientEndpoint.jar ClientEndpoint.class
cd ..


echo "> compiling server object files"
javac -d classes -classpath .:classes src/ChatService.java
cd classes
jar cf ../lib/ChatService.jar ChatService.class
cd ..


echo "> compiling server interface implementation"
javac -d classes -classpath .:classes src/ChatServiceImpl.java
cd classes
jar cf ../lib/ChatServiceImpl.jar ChatServiceImpl.class
cd ..

echo "> compiling client interface implementation"
javac -d classes -classpath .:classes src/ClientEndpointImpl.java
cd classes
jar cf ../lib/ClientEndpointImpl.jar ClientEndpointImpl.class
cd ..


echo "> compiling server"
javac -d classes -cp .:classes:lib/ChatService.jar:lib/ChatServiceImpl.jar src/ChatServer.java

echo "> compiling client"
javac -d classes -cp .:classes:lib/ChatService.jar:lib/ClientEndpointImpl.jar src/ChatClient.java

cp="classes"

#Can not be run in subshell
echo "*Done* Remember to run 'export CLASSPATH=$cp'"
