FROM openjdk:8-jdk-alpine
MAINTAINER xiaobingzhou xiaobing9468@gmail.com
VOLUME /tmp
ADD message-frame-sample.jar  /app.jar
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
