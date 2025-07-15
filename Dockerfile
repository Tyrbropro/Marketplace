FROM gradle:8.5-jdk21 AS builder
WORKDIR /app
COPY . .
RUN gradle clean \
    :order-service:bootJar \
    :user-service:bootJar \
    :notification-service:bootJar \
    --no-daemon

FROM eclipse-temurin:21-jre AS order-service
WORKDIR /app
COPY --from=builder /app/order-service/build/libs/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

FROM eclipse-temurin:21-jre AS user-service
WORKDIR /app
COPY --from=builder /app/user-service/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

FROM eclipse-temurin:21-jre AS notification-service
WORKDIR /app
COPY --from=builder /app/notification-service/build/libs/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
