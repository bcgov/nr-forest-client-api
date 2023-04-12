FROM eclipse-temurin:17.0.6_10-jdk-alpine@sha256:a765a97826df90554f3d3a98be5586012bbc53593876f669ff4b2e68717be71d

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
