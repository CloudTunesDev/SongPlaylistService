# Create environment
FROM openjdk:17-jdk-slim as build
WORKDIR /app

# Copy the project files
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src

# Build the project (+ exclude tests)
RUN ./gradlew build -x test


# Runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR file
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]