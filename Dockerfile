FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY app.jar app.jar
EXPOSE 8081
CMD ["java", "-Xmx384m", "-Xms128m", "-jar", "app.jar"]