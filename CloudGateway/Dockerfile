FROM openjdk:latest

ENV EUREKA_SERVER_ADDRESS=http://host.docker.internal:8761/eureka
ENV CONFIG_SERVER_URL=host.docker.internal:9296

COPY build/libs/*.jar cloud-gateway.jar

ENTRYPOINT ["java", "-jar", "cloud-gateway.jar"]

EXPOSE 9090