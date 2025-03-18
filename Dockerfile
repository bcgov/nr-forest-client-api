### Builder
FROM ghcr.io/graalvm/native-image:ol8-java17-22.3.3 AS build

# Install Maven
# Add Fedora EPEL repo and install newer Maven
RUN microdnf install -y oraclelinux-release-el8 epel-release && \
    microdnf update -y --enablerepo=ol8_developer && \
    microdnf install -y --enablerepo=ol8_developer maven && \
    microdnf clean all

RUN mvn -v

# Receiving app version
ARG APP_VERSION=0.0.1

# Copy
WORKDIR /app
COPY pom.xml ./
COPY src ./src
COPY mvnw ./
COPY .mvn/wrapper/maven-wrapper.properties ./.mvn/wrapper/maven-wrapper.properties

# Setting app version
RUN mvn versions:set -DnewVersion=${APP_VERSION} -f pom.xml -DskipTests -Dtests.skip=true -Dskip.unit.tests=true && \
    mvn versions:commit -f pom.xml -DskipTests -Dtests.skip=true -Dskip.unit.tests=true

# Build
RUN ./mvnw -Pnative native:compile


### Deployer
FROM gcr.io/distroless/java-base:nonroot AS deploy
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