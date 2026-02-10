#!/bin/bash

# run the chatserver
java -classpath .:classes:lib/Hello.jar:lib/ChatServiceImpl.jar ChatServer
