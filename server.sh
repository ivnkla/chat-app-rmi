#!/bin/bash

# run the chatserver
java -classpath .:classes:lib/Message.jar:lib/ChatService.jar:lib/ChatServiceImpl.jar:lib/Deserializer.jar server.ChatServer