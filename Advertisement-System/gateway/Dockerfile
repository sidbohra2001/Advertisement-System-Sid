FROM eclipse-temurin:latest as builder
WORKDIR /Advertisement-System/dateway
COPY mvnw mvnw
RUN sed -i 's/\r$//' mvnw
COPY pom.xml pom.xml
COPY .mvn .mvn
RUN mvn clean install -Dmaven.test.skip

FROM wonderd/jre-alpine
WORKDIR /Advertisement-System/gateway
COPY /target/gateway.jar /target/gateway.jar
CMD ["java", "-jar", "/target/gateway.jar"]