FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

ENV LANG en_CA.UTF-8
ENV LANGUAGE en_CA.UTF-8
ENV LC_ALL en_CA.UTF-8
ENV JAVA_OPS -Xms512m -Xmx512m

COPY startup.sh .
COPY InstallCert.java .

RUN chmod g+w /app && \
    chmod g+x startup.sh && \
    chmod g+w ${JAVA_HOME}/lib/security/cacerts

EXPOSE 3001

HEALTHCHECK --interval=35s --timeout=4s CMD wget --spider -S http://127.0.0.1:3001/health || exit 1

# Non-privileged user
USER app

ENTRYPOINT ["sh", "startup.sh"]