FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven build files and source code
COPY pom.xml .
COPY src ./src

# Install Maven and build the application
RUN apt-get update && apt-get install -y maven \
    && mvn clean package -DskipTests

# Copy the built JAR file
COPY target/*.jar app.jar

# Expose the port for the Spring Boot application
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "appointment-app.jar"]