FROM openjdk:17-jdk-slim
COPY ./target/classes /app/classes
WORKDIR /app
ENTRYPOINT ["java", "-cp", "classes", "com.napier.pr.Main"]