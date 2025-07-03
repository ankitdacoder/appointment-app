FROM openjdk:17-alpine
EXPOSE 8080
ADD target/appointment-app.jar appointment-app.jar
ENTRYPOINT ["java","-jar","/appointment-app.jar"]