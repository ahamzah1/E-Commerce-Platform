# Stage 1: Build (with Java 21)
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
# 1. Copy POM first for dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline

# 2. Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# 3. Copy the EXACT JAR name matching your pom.xml
COPY --from=builder /app/target/OrderService-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]