FROM openjdk:latest

ENV DB_HOST=host.docker.internal
ENV EUREKA_SERVER_ADDRESS=http://host.docker.internal:8761/eureka
ENV CONFIG_SERVER_URL=host.docker.internal:9296

COPY build/libs/*.jar product-service.jar

ENTRYPOINT ["java", "-jar", "product-service.jar"]

EXPOSE 8080