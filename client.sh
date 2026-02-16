#!/bin/bash

#run the chatclient
java -classpath .:classes:lib/Message.jar:lib/ChatService.jar:lib/ClientEndpointImpl.jar client.ChatClient localhost 0
