FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/transaction-service-1.0.0.jar transaction-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "transaction-service.jar"]
