FROM eclipse-temurin:17.0.7_7-jdk-alpine@sha256:ad135147f78ddb330275438075a7177aaf9408f62d6b97ad2ecb6e66c1adc7b9

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
