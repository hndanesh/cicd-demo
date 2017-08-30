FROM openjdk:8-alpine

ADD build/libs/gs-spring-boot-0.1.0.jar /opt/

ENTRYPOINT ["java", "-jar", "/opt/gs-spring-boot-0.1.0.jar"]
