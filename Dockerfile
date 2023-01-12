FROM amazoncorretto:17-alpine-jdk

MAINTAINER NDM

COPY target/NDM-0.0.1-SNAPSHOT.jar NDM-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/NDM-0.0.1-SNAPSHOT.jar"]