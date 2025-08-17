FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /pedidos-cliente
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /pedidos-cliente/target/pedido-microservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
