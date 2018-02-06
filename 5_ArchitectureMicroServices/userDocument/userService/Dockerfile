FROM java:8-jre
MAINTAINER David THIBAU <david.thibau@gmail.com>

ADD ./target/userService.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/userService.jar"]

EXPOSE 2222
