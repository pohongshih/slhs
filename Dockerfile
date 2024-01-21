FROM openjdk:17-jdk-slim
WORKDIR /app
COPY pom.xml ./
COPY /target/*.jar /slhs.jar
CMD ["java", "-jar", "/slhs.jar"]
