FROM adoptopenjdk/openjdk11
VOLUME /tmp
COPY target/pesquisei-0.1*.jar app.jar
EXPOSE 8060
ENTRYPOINT ["java", "-jar", "/app.jar"]