# --- ETAPA 1: Compilación ---
FROM maven:3.9-eclipse-temurin-17-alpine AS builder
WORKDIR /app

# CORRECCIÓN: Le indicamos a Docker la ruta exacta desde la raíz del repositorio
# Cambia "backend/" por el nombre real de tu carpeta si es distinto.
COPY pom.xml .
RUN mvn dependency:go-offline -B

# CORRECCIÓN: Hacemos lo mismo con el código fuente
COPY src ./src
RUN mvn clean package -DskipTests

# --- ETAPA 2: Ejecución ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Aquí no hace falta cambiar nada, porque esta etapa copia el .jar 
# desde el contenedor "builder" temporal, no desde tu repositorio de GitHub.
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]