# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/GlobalDorm-0.0.1-SNAPSHOT.jar backend.jar

# Expose the port that the Spring Boot application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "backend.jar"]
