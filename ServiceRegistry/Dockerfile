FROM openjdk:latest
COPY build/libs/*.jar service-registry.jar
ENTRYPOINT ["java", "-jar", "/service-registry.jar"]
EXPOSE 8761