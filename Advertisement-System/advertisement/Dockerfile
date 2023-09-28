FROM eclipse-temurin:latest as builder
WORKDIR /Advertisement-System/advertisement
COPY .mvnw .mvnw
RUN sed -i 's/\r$//' mvnw
COPY pom.xml pom.xml
COPY .mvn .mvn
RUN mvn clean install -Dmaven.test.skip

FROM wonderd/jre-alpine
WORKDIR /Advertisement-System/advertisement
COPY /target/advertisement.jar /target/advertisement.jar
CMD ["java", "-jar", "/target/advertisement.jar"]