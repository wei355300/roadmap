#!/bin/sh

mvn clean && mvn package -Dmaven.test.skip=true

docker build -t mantas/tapd:latest .
