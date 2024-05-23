FROM amazoncorretto:17-alpine-jdk
LABEL maintainer="thy-fintech";

EXPOSE 8086

COPY target/bootcamp-0.0.1-SNAPSHOT.jar  thyfintech.jar

ENTRYPOINT ["java","-jar","/thyfintech.jar"]
