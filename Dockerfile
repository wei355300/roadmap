FROM registry.cn-hangzhou.aliyuncs.com/petkit-saas/java:latest

MAINTAINER "mantas.cn" <mantas.cn>

ARG BASE_DIR=/proj/server/

ADD target/roadmap.jar /proj/server/roadmap.jar

#COPY start.sh /

#RUN cd / && \
#    cp start.sh start.sh; \
#    fi

#RUN chmod a+x /start.sh
#ENTRYPOINT ["/start.sh"]

ENTRYPOINT ["java", "-jar", "/proj/server/roadmap.jar"]

EXPOSE 8080
