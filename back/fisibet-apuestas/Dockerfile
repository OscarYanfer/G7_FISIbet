# Fetching latest version of Java
FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/fisibet-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} fisibetusers.jar
ENTRYPOINT ["java","-jar","/fisibetusers.jar"]