FROM openjdk:8
EXPOSE 2020
ADD=target/Employee_Service-0.0.1-SNAPSHOT.jar Employee_Service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Employee_Service-0.0.1-SNAPSHOT.jar"]