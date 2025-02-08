# Use openjdk base image
FROM openjdk:17-jdk-slim

# Set working directory in container
WORKDIR /app

# Copy the jar file into the container
COPY target/webApplication-oadin.jar /app/webApplication-oadin.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "webApplication-oadin.jar"]
