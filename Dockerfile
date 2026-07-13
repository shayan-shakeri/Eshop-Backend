
FROM eclipse-temurin:21-jre-alpine


WORKDIR /app


COPY build/libs/eshop-all.jar app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]