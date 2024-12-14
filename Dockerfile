# Use an official Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jre

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file from the build stage
COPY --from=build /app/target/restaurante-api.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080 5005

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"]