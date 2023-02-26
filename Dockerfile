FROM maven:3.8.2-eclipse-temurin-17

COPY . .

RUN mvn clean install

CMD [ "mvn","spring-boot:run" ]
# ADD target/DefineX-Practicum-Final-Case-0.0.1-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java","-jar","app.jar"]
