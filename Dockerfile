FROM amazoncorretto:17
COPY ./target/pr.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "pr.jar", "db:3306", "30000"]