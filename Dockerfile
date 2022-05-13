FROM arm64v8/openjdk:11.0.15-jdk-oraclelinux8

MAINTAINER "mantas.cn" <mantas.cn>

ARG BASE_DIR=/proj/server/

ADD target/roadmap.jar /proj/server/roadmap.jar

ENTRYPOINT ["java", "-jar", "/proj/server/roadmap.jar"]

EXPOSE 8080
