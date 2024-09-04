FROM openjdk:17
WORKDIR /app
COPY target/productservice.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
