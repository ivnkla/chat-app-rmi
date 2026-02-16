#!/bin/bash
pkill -f rmiregistry

export CLASSPATH=classes
rmiregistry &