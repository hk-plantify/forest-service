FROM openjdk:17-jdk-slim
COPY ./build/libs/*-SNAPSHOT-plain.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]