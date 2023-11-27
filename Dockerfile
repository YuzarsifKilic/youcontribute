FROM eclipse-temurin:20-alpine as builder

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY . .

RUN ./gradlew build -x test

FROM eclipse-temurin:20-jre-alpine as runner

COPY --from=builder /usr/src/app/build/libs/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]