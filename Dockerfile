FROM openjdk:8

#copy jar file with application to the container
ARG JAR_FILE=target/rea-crawler-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

#copy firestore credentials file to the container
COPY rea-firestore.json rea-firestore.json

#set environment variables
ARG VENDOR
ENV VENDOR ${VENDOR}
ENV FIRESTORE_CREDENTIALS_PATH /rea-firestore.json
ENV FIRESTORE_PROJECT_ID real-estate-aggregator-accf5

#start the app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]