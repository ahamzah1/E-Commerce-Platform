# E-commerce Platform

This project is an **educational e-commerce platform** built to demonstrate my skills in **microservices architecture**, **Docker Compose** for development, and **Kubernetes** for production. It utilizes **Spring Boot** for creating microservices, **Eureka** for service discovery, and an **API Gateway** for routing requests. The platform also incorporates **inter-service communication** using both **REST APIs** and **messaging systems** (Kafka).

---

## Features

- **Microservices Architecture**: The platform is divided into multiple independent services, each responsible for a specific business capability.
- **Service Discovery**: Uses **Eureka** to dynamically discover and communicate with services.
- **API Gateway**: Centralized entry point for all client requests, implemented using **Spring Cloud Gateway**.
- **Inter-Service Communication**: Supports both **REST APIs** and **messaging systems** (Kafka) for asynchronous communication.
- **Docker Compose for Development**: Local development environment with all services containerized using Docker Compose.
- **Kubernetes for Production**: Production-ready deployment using Kubernetes for scalability and high availability.
- **Database Integration**: Uses **PostgreSQL** and **MongoDB** for persistent data storage.
- **Notification Service**: Integrates with **MailDev** for email notifications during development.

---

## Architecture Overview

The platform consists of the following microservices:

1. **Cutomer Service**: Manages Cutomer authentication and profiles.
2. **Product Service**: Handles product catalog and inventory.
3. **Order Service**: Manages order creation and processing.
4. **Payment Service**: Processes payments and updates order status.
5. **Notification Service**: Sends email notifications for order updates.
6. **API Gateway**: Routes client requests to the appropriate services.
7. **Eureka Server**: Provides service discovery for all microservices.

---

## Technologies Used

- **Spring Boot**: For building microservices.
- **Spring Cloud Netflix Eureka**: For service discovery.
- **Spring Cloud Gateway**: For API Gateway.
- **Docker Compose**: For local development and testing.
- **Kubernetes**: For production deployment.
- **PostgreSQL**: Relational database for user and order data.
- **MongoDB**: NoSQL database for product catalog.
- **Kafka**: Messaging system for asynchronous communication.
- **MailDev**: For testing email notifications during development.