# Start with a base image containing Java runtime (JDK 17 in this case)
FROM eclipse-temurin:17-jdk as build

# Add Maintainer Info
LABEL maintainer="example@gmail.com"

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=api/build/libs/*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
