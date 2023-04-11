FROM alpine:3.17.3@sha256:b6ca290b6b4cdcca5b3db3ffa338ee0285c11744b4a6abaa9627746ee3291d8d

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
