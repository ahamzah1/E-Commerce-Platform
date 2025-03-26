# Stage 1: Build (with Java 21)
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Build
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run (with Java 21 JRE)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/target/config-server-*.jar app.jar

EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]