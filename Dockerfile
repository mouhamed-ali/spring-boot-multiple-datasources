# Create a first container to package the app
FROM maven:3.6.3-jdk-8-slim as BUILDER

WORKDIR /app

COPY . /app/

RUN mvn package

# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine

# copy application JAR (with libraries inside)
COPY --from=BUILDER /app/target/skills-referential.jar /app.jar

# expose the spring boot default port
EXPOSE 8080

# specify default command
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=dev", "/app.jar"]