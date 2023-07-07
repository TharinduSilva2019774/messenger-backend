FROM openjdk:17
COPY target/messenger-0.0.1-SNAPSHOT.jar messenger-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/messenger-0.0.1-SNAPSHOT.jar"]