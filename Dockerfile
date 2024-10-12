# Use an official OpenJDK image as a base
FROM openjdk:11-jre-slim

# Add a volume pointing to /tmp
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=target/tms-0.0.1-SNAPSHOT.jar

# Copy the jar file to the container
COPY ${JAR_FILE} app.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]