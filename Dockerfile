### Builder
FROM ghcr.io/graalvm/native-image:ol9-java17-22.3.3@sha256:4aeee052a80237fa8b32c074d2b7b7adc92271fc4bda724f0b1c2ea0f2f884cb AS build

# App version
ARG APP_VERSION=0.0.1

# Copy
WORKDIR /app
COPY pom.xml ./
COPY src ./src
COPY mvnw ./
COPY .mvn/wrapper/maven-wrapper.properties ./.mvn/wrapper/maven-wrapper.properties

# Ensure mvnw has execution permissions
RUN chmod +x mvnw

# Setting app version
RUN ./mvnw versions:set -DnewVersion=${APP_VERSION} -f pom.xml -DskipTests -Dtests.skip=true -Dskip.unit.tests=true && \
    ./mvnw versions:commit -f pom.xml -DskipTests -Dtests.skip=true -Dskip.unit.tests=true

# Build
RUN ./mvnw -Pnative native:compile


### Deployer
FROM gcr.io/distroless/java-base:nonroot@sha256:1e0badbf909aeefa2459b3987c4cdc788ed5ead4f5a360f6e92129e92843d034 AS deploy
ARG PORT=3001

# Copy
WORKDIR /app
COPY --from=build /app/target/nr-forest-client-api ./nr-forest-client-api

# User, port and health check
USER 1001
EXPOSE ${PORT}
HEALTHCHECK CMD curl -f http://localhost:${PORT}/actuator/health | grep '"status":"UP"'

# Startup
ENTRYPOINT ["/app/nr-forest-client-api","--spring.profiles.active=container"]