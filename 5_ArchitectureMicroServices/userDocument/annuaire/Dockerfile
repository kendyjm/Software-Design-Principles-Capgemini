FROM java:8-jre
MAINTAINER David Thibau <david.thibau@gmail.com>

ADD ./target/annuaire.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/annuaire.jar"]

EXPOSE 1111
