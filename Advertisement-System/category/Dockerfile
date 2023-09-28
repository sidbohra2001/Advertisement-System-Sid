FROM eclipse-temurin:latest as builder
WORKDIR /Advertisement-System/category
COPY mvnw mvnw
RUN sed -i 's/\r$//' mvnw
COPY pom.xml pom.xml
COPY .mvn .mvn
RUN mvn clean install -Dmaven.test.skip

FROM wonderd/jre-alpine
WORKDIR /Advertisement-System/category
COPY /target/category.jar /target/category.jar
CMD ["java", "-jar", "/target/category.jar"]