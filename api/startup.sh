#!/bin/sh

java --source 17 InstallCert.java --quiet "${ORACLEDB_HOST}:${ORACLEDB_PORT}"
keytool -exportcert -alias "${ORACLEDB_HOST}-1" -keystore jssecacerts -storepass changeit -file oracle.cer
keytool -importcert -alias orakey -noprompt -cacerts -storepass changeit -file oracle.cer

java -Djava.security.egd=file:/dev/./urandom -jar /app/service.jar

