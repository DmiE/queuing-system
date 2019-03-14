FROM openjdk:8-jdk-alpine as build
WORKDIR /usr/share/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
RUN mv ./target/*.jar ./target/qmanager-database-api.jar
CMD java -jar ./target/qmanager-database-api.jar
