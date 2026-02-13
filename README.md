# chat-app-rmi
This app provides a simple CLI client-server module behaving like a realtime forum chatting app. It follows (or at least try to follow) the RMI principles.

## how to run ?
It not yet installed do:
```
$ git clone https://github.com/ivnkla/chat-app-rmi.git
```
Then launch the build script and follow the instructions. You may need to add permissions.
```
$ chmod u+x build.sh server.sh client.sh rmi.sh
```
```
$ ./build.sh
```

## features
* client can join, broadcast messages and leave
* persistent history on the server
