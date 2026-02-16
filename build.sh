#!/bin/bash

# compiler chaque jar à la main devenait un peu overkill ... voici une nouvelle version

echo "> cleaning up"
rm -rf classes lib
mkdir -p classes lib

echo "> compiling ..."
javac -d classes src/common/*.java src/server/*.java src/client/*.java

echo "> creating individual jars"
cd classes
jar cf ../lib/Message.jar common/Message.class
jar cf ../lib/ClientEndpoint.jar common/ClientEndpoint.class
jar cf ../lib/ChatService.jar common/ChatService.class
jar cf ../lib/Deserializer.jar common/Deserializer.class
jar cf ../lib/ChatServiceImpl.jar server/ChatServiceImpl.class
jar cf ../lib/ClientEndpointImpl.jar client/ClientEndpointImpl.class

cd ..
echo "*Done*"