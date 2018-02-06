FROM java:8-jre
MAINTAINER David THIBAU <david.thibau@gmail.com>

ADD ./target/documentService.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/documentService.jar"]

EXPOSE 3333
