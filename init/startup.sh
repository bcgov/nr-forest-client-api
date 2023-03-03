#!/bin/sh

mkdir -p /cert/certs
rm -rf /cert/certs/*

echo "I will try to get the ${ORACLEDB_HOST}-1 cert"
echo "Connecting to ${ORACLEDB_HOST}:${ORACLEDB_PORT}"
java --source 17 InstallCert.java --quiet "${ORACLEDB_HOST}:${ORACLEDB_PORT}"

cp jssecacerts /cert/jssecacerts