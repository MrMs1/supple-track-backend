FROM amazoncorretto:17-alpine as builder
WORKDIR /app
COPY app ./app
COPY gradle ./gradle
COPY build.gradle settings.gradle gradlew ./
RUN ./gradlew :app:bootJar -x :flyway:generateJooq --no-daemon

FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=builder /app/app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]