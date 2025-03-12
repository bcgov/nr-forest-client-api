### Builder
FROM ghcr.io/graalvm/native-image:ol8-java17-22.3.3 AS build

# Install Maven
RUN microdnf update -y && \
    microdnf install -y maven && \
    microdnf clean all

# Receive app version
ARG APP_VERSION=0.0.1

# Copy
WORKDIR /app
COPY pom.xml ./
COPY src ./src
COPY mvnw ./
COPY .mvn/wrapper/maven-wrapper.properties ./.mvn/wrapper/maven-wrapper.properties

# Debug log
RUN echo "Building version ${APP_VERSION}"

# Build
RUN ./mvnw versions:set -DnewVersion=${APP_VERSION} -f pom.xml -DskipTests && \
    ./mvnw versions:commit -f pom.xml -DskipTests

# Build native image
RUN ./mvnw -Pnative native:compile

### Deployer
FROM gcr.io/distroless/java-base:nonroot AS deploy

# Receive port argument
ARG PORT=3001

# Copy the compiled native binary from the build stage
WORKDIR /app
COPY --from=build /app/target/nr-forest-client-api ./nr-forest-client-api

# Set non-root user, expose port, and health check
USER 1001
EXPOSE ${PORT}
HEALTHCHECK CMD curl -f http://localhost:${PORT}/actuator/health | grep '"status":"UP"'

# Start application
ENTRYPOINT ["/app/nr-forest-client-api", "--spring.profiles.active=container"]
