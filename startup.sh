#!/bin/sh

cert_folder="/cert"
cert_file="jssecacerts"

mkdir -p $cert_folder

generate_cert() {
  echo "I will try to get the ${ORACLEDB_HOST}-1 cert"
  echo "Connecting to ${ORACLEDB_HOST}:${ORACLEDB_PORT}"
  java --source 17 InstallCert.java --quiet "${ORACLEDB_HOST}:${ORACLEDB_PORT}"
  cp "$cert_file" "$cert_folder/$cert_file"
  echo "Generated $cert_file and copied it to $cert_folder."
}

if [ "$(ls -A $cert_folder)" ]; then
  echo "The $cert_folder folder is not empty."
  if [ -e "$cert_folder/$cert_file" ]; then
    echo "The "$cert_folder/$cert_file" certificate file is present."
  else
    generate_cert
  fi
else
  generate_cert
fi
