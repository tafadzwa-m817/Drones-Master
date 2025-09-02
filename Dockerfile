FROM openjdk:17-jdk-slim

MAINTAINER romeojerenyama@gmail.com

WORKDIR /app

COPY target/drones.jar /app/drones.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "drones.jar"]