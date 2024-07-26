FROM maven:3.8.5-openjdk-17 AS build
COPY .. .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build target/*.jar demo.jar
RUN mkdir /uploads && chmod -R a+rw /uploads
EXPOSE 8080
CMD ["java", "-jar", "demo.jar"]