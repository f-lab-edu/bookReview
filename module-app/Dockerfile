FROM gradle:8.5-jdk17 AS builder

WORKDIR /app
COPY . /app

RUN gradle build -x test

FROM openjdk:17
WORKDIR /app

COPY --from=builder /app/module-app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
