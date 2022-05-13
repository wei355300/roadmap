#!/bin/sh

mvn clean && mvn package -Dmaven.test.skip=true

docker build -f Dockerfile -t mantas/roadmap-java:latest .
