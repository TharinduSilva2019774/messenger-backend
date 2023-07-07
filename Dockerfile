FROM maven:3.8.3-openjdk-17 AS DEPS

WORKDIR /opt/app

COPY pom.xml .
COPY ./messengerBackend/pom.xml messengerBackend/pom.xml

RUN mvn dependency:go-offline -B

FROM maven:3.8.3-openjdk-17 as BUILDER

WORKDIR /opt/app

COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /opt/app/ /opt/app

COPY messengerBackend/src /opt/app/messengerBackend/src

RUN mvn package -B -DskipTests=true

FROM gcr.io/distroless/java17-debian11

WORKDIR /opt/app

COPY --from=builder /opt/app/leave-planner/target/*.jar sen-app.jar

ENTRYPOINT ["java", "-jar", "/opt/app/sen-app.jar"]