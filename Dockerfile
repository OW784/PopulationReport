FROM openjdk:latest
COPY ./target/PopulationReport-1.0-SNAPSHOT.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]