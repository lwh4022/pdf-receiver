FROM openjdk:8 AS builder

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM openjdk:8

ARG JAR_PATH=/home/app.jar
COPY --from=builder build/libs/*.jar ${JAR_PATH}

ARG KEY_FILE_PATH=pdf-receiver.json
COPY ${KEY_FILE_PATH} ${KEY_FILE_PATH}

ENV GOOGLE_APPLICATION_CREDENTIALS=${KEY_FILE_PATH}

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/app.jar"]