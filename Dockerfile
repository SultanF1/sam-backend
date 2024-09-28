# Use an official Gradle image to build the application
# Assuming a custom Gradle image that supports JDK 22 is available
FROM gradle:jdk22 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the build.gradle file and gradle wrapper to the working directory
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Copy the application source code to the working directory
COPY src ./src

# Build the application
RUN ./gradlew build --no-daemon -x test

# Use a suitable base image for running the application with JDK 22
# Assuming a custom JDK 22 runtime image is available
FROM amazoncorretto:22-alpine
LABEL authors="sultanrazin"


# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file from the build stage to the working directory
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]