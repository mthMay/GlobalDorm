# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} backend.jar

# Expose the port that the Spring Boot application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "backend.jar"]
