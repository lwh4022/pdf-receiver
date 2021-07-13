FROM openjdk:8

RUN mkdir /home/server
WORKDIR /home/server

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM openjdk:8
COPY --from=builder build/libs/*.jar app.jar
COPY pdf-receiver.json .

RUN export GOOGLE_APPLICATION_CREDENTIALS=./pdf-receiver.json

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]