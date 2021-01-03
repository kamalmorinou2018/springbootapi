FROM adoptopenjdk/openjdk11:ubi
COPY target/DemoSpring.jar DemoSpring.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "DemoSpring.jar"]