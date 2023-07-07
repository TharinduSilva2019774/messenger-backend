FROM openjdk:17
COPY target/*.jar messenger-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/messenger-0.0.1-SNAPSHOT.jar"]