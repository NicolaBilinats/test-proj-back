FROM openjdk:17-jdk-slim

WORKDIR /app
COPY pom.xml .
COPY . .
RUN mvn package
ENV JAR_FILE=target/test-proj-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "$JAR_FILE"]
