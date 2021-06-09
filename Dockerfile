FROM openjdk:8
EXPOSE 8080
ADD target/demo-0.0.1-SNAPSHOT.jar afkar.jar
ENTRYPOINT ["java","-jar","/afkar.jar"]
