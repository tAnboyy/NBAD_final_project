# Build stage
FROM maven:3.9.0-eclipse-temurin-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -DskipTests package

# Run stage
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 3000
ENV SERVER_PORT=3000
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${SERVER_PORT} -jar /app/app.jar"]