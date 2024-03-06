# Use base image of Java 17
FROM openjdk:17-jdk-slim

# Install protobuf compiler
RUN apt-get update && apt-get install -y protobuf-compiler

# Set the working directory in the container
WORKDIR /app

# Copy the proto file(s) into the container
COPY src/main/proto/*.proto .

# Compile the proto file(s)
RUN protoc --java_out=. *.proto