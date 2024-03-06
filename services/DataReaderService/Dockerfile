# Use base image of Java 17
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/DataReaderService-1.0-SNAPSHOT.jar /app/data-reader-service.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "data-reader-service.jar"]
