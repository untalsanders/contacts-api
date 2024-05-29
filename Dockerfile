FROM azul/zulu-openjdk-alpine:17
MAINTAINER Sanders Gutiérrez <ing.sanders@gmail.com>
RUN mkdir /opt/app
COPY build/libs/*.jar /opt/app/app.jar
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
