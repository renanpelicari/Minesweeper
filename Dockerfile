FROM openjdk:17-jdk
EXPOSE 8088
WORKDIR /var/app
COPY build/libs/minesweeper-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dserver.port=8088", "-jar", "app.jar"]