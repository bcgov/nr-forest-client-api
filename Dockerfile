FROM openjdk:17 as build
WORKDIR /app
COPY . ./
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests -Dtests.skip=true -Dskip.unit.tests=true

FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer="Paulo Gomes da Cruz Junior <paulo.cruz@encora.com>"

WORKDIR /app

ENV LANG en_CA.UTF-8
ENV LANGUAGE en_CA.UTF-8
ENV LC_ALL en_CA.UTF-8
ENV JAVA_OPS -Xms512m -Xmx512m

COPY --from=build /app/target/nr-forest-client-api.jar /app/service.jar
COPY startup.sh .
COPY InstallCert.java .

RUN chmod g+w /app && \
    chmod g+x startup.sh && \
    chmod g+w ${JAVA_HOME}/lib/security/cacerts

EXPOSE 3001

# Non-privileged user
USER app

ENTRYPOINT ["sh", "startup.sh"]