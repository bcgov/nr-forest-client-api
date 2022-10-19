FROM docker.io/openjdk:17-alpine
WORKDIR /app
COPY . ./

ENV LANG en_CA.UTF-8
ENV LANGUAGE en_CA.UTF-8
ENV LC_ALL en_CA.UTF-8
ENV JAVA_OPS -Xms256m -Xmx512m

RUN ./mvnw clean package
RUN chmod -R 777 ./target/*.jar
RUN chmod -R g+w . && \
    chmod g+x startup.sh && \
    chmod g+w /opt/openjdk-17/lib/security/cacerts

EXPOSE 3000

ENTRYPOINT ["sh", "startup.sh"]