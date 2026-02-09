#!/bin/bash


#java -classpath .:classes:lib/Hello.jar:lib/HelloImpl.jar HelloServer

java -classpath .:classes:lib/Hello.jar ChatClient localhost 0
