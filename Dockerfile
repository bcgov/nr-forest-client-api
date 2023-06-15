FROM eclipse-temurin:17.0.7_7-jdk-alpine@sha256:2478889d707f1883cd7c2682bba048b680261b09088c4af8ae735bc1d8a10d55

ENV LANG en_CA.UTF-8
ENV LANGUAGE en_CA.UTF-8
ENV LC_ALL en_CA.UTF-8

WORKDIR /app

RUN apk --no-cache add openssl

COPY startup.sh .

RUN chmod g+w /app && \
    chmod g+x startup.sh && \
    chmod g+w ${JAVA_HOME}/lib/security/cacerts

# Non-privileged user
USER app

ENTRYPOINT ["sh", "startup.sh"]
