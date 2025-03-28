FROM openjdk:17
WORKDIR /app
COPY build/libs/module-app-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
