FROM openjdk:17
COPY /opt/app/messengerBackend/target/*.jar sen-app.jar
ENTRYPOINT ["java", "-jar", "/opt/app/sen-app.jar"]

