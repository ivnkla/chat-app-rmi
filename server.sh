#!/bin/bash


java -classpath .:classes:lib/Hello.jar:lib/ChatServiceImpl.jar ChatServer
#java -classpath .:classes:lib/Hello.jar HelloClient localhost 0
