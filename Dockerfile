FROM docker.io/openjdk:17-alpine
WORKDIR /app
COPY . ./

ENV LANG en_CA.UTF-8
ENV LANGUAGE en_CA.UTF-8
ENV LC_ALL en_CA.UTF-8
ENV JAVA_OPS -Xms256m -Xmx512m

EXPOSE 3000

ENTRYPOINT ["sh", "startup.sh"]