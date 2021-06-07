FROM openjdk:8
ADD target/docker-book-store.jar docker-book-store.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "docker-book-store.jar"]
