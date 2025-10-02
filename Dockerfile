FROM openjdk:latest
COPY ./target/PopulationReport-1.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]