FROM openjdk:latest
COPY ./target/PopulationReport-0.2-jar-with-dependencies.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]