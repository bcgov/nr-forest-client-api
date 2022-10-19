#!/bin/sh

java --source 17 InstallCert.java --quiet "${ORACLEDB_HOST}:${ORACLEDB_PORT}"
keytool -exportcert -alias "${ORACLEDB_HOST}-1" -keystore jssecacerts -storepass changeit -file oracle.cer
keytool -importcert -alias orakey -noprompt -cacerts -storepass changeit -file oracle.cer

./mvnw clean package
java -jar target/nr-forest-client-api.jar