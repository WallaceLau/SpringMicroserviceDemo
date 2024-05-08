FROM openjdk:latest

ENV EUREKA_SERVER_ADDRESS=http://host.docker.internal:8761/eureka
COPY build/libs/*.jar config-server.jar

ENTRYPOINT ["java", "-jar", "config-server.jar"]

EXPOSE 9296