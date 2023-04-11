FROM alpine:latest

ENV LANG en_CA.UTF-8
ENV LANGUAGE en_CA.UTF-8
ENV LC_ALL en_CA.UTF-8

RUN apk --no-cache add openssl openjdk8

RUN which openssl
RUN which keytool

COPY startup.sh .

RUN chmod g+x startup.sh

# Non-privileged user
USER app

ENTRYPOINT ["sh", "startup.sh"]
