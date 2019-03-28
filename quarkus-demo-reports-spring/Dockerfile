FROM openjdk:8-jdk-alpine
COPY target/*SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]