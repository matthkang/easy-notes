FROM openjdk:11
EXPOSE 8080
LABEL service=app
ARG JAR_FILE=target/easy-notes-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]