FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8080
ADD target/relationship.jar relationship.jar
ENTRYPOINT ["java","-jar","/relationship.jar"]