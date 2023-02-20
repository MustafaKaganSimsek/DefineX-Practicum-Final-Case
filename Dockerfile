FROM openjdk:17-jdk-slim-buster
ADD target/DefineX-Practicum-Final-Case-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
