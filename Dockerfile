# ==========================================================================
# Etapa 1: Compilación del proyecto (Maven)
# ==========================================================================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos el archivo de configuración de dependencias
COPY pom.xml .

# Copiamos el código fuente de la app
COPY src ./src

# Compilamos el archivo .jar saltando los tests para acelerar el despliegue
RUN mvn clean package -DskipTests

# ==========================================================================
# Etapa 2: Imagen final de ejecución (Súper liviana)
# ==========================================================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos el .jar generado en la etapa anterior (revisá que el nombre coincida)
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto en el contenedor
EXPOSE 8080

# Configuramos el límite de memoria para que Render no mate el proceso (300MB)
ENV JAVA_TOOL_OPTIONS="-Xmx300m -Xms300m"

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]