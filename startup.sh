#!/bin/sh

cert_folder="/cert"
cert_file="jssecacerts"

mkdir -p $cert_folder

generate_cert() {

  echo "I will try to get the ${ORACLEDB_HOST}-1 cert"
  echo "Connecting to ${ORACLEDB_HOST}:${ORACLEDB_PORT}"

  openssl s_client -connect ${ORACLEDB_HOST}:${ORACLEDB_PORT} -showcerts </dev/null | openssl x509 -outform pem > $cert_folder/${ORACLEDB_HOST}.pem
  openssl x509 -outform der -in $cert_folder/${ORACLEDB_HOST}.pem -out $cert_folder/${ORACLEDB_HOST}.der
  keytool -import -alias ${ORACLEDB_HOST} -keystore $cert_folder/$cert_file -file $cert_folder/${ORACLEDB_HOST}.der -storepass ${ORACLEDB_SECRET} -noprompt

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

