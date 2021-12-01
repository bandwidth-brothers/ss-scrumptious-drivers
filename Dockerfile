FROM maven:latest
COPY target/ss-scrumptious-drivers-0.0.1-SNAPSHOT.jar /home/driver-service.jar
ENTRYPOINT java -jar /home/driver-service.jar
