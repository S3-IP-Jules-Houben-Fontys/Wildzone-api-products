FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb:27017/wildzone-products","-jar","/app.jar"]