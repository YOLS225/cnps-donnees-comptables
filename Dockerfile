FROM gradle:jdk21 AS build

COPY . /home/gradle/src

WORKDIR /home/gradle/src

#RUN gradle build -x test
RUN gradle build

FROM openjdk:21-jdk-slim-buster

EXPOSE 9000

RUN groupadd -r cnps && useradd -r -g cnps cnps

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

USER cnps

ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]