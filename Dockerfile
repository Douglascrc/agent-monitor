FROM eclipse-temurin:21-jdk-alpine
ADD target/agent-monitor-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app.jar"]