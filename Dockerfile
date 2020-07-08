
FROM openjdk:8-jdk-alpine
RUN apk add tzdata && ls /usr/share/zoneinfo && cp /usr/share/zoneinfo/Asia/Seoul /etc/localtime && echo "Asia/Seoul" > /etc/timezone
VOLUME ["/tmp", "/logs"]
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]