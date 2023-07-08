# Use a base image with Java and Maven installed
FROM maven:3.8.1-openjdk-17-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code to the container
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Create a new image with only the necessary files
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage to the current directory
COPY --from=build /app/target/my-application.jar .

# Expose the port that the application will run on
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "my-application.jar"]