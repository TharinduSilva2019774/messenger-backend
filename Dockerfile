FROM maven:3.8.3-openjdk-17 AS DEPS

WORKDIR /opt/app

COPY pom.xml .
COPY ./leave-planner/pom.xml leave-planner/pom.xml
COPY ./resource-planner/pom.xml resource-planner/pom.xml

RUN mvn dependency:go-offline -B

FROM maven:3.8.3-openjdk-17 as BUILDER

WORKDIR /opt/app

COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /opt/app/ /opt/app

COPY leave-planner/src /opt/app/leave-planner/src
COPY resource-planner/src /opt/app/resource-planner/src

RUN mvn package -B -DskipTests=true

FROM gcr.io/distroless/java17-debian11

WORKDIR /opt/app

COPY --from=builder /opt/app/leave-planner/target/*.jar leave-module-app.jar

ENTRYPOINT ["java", "-jar", "/opt/app/leave-module-app.jar"]