# RedHat UBI 8 with nodejs 16
FROM registry.access.redhat.com/ubi8/ubi
RUN dnf module install -y nodejs:16 

# Install required packages for oracle client
RUN dnf install -y wget unzip libaio nc iputils yum net-tools
RUN mkdir -p /opt/oracle && \
    cd /opt/oracle && \
    wget https://download.oracle.com/otn_software/linux/instantclient/instantclient-basiclite-linuxx64.zip && \
    unzip instantclient-basiclite-linuxx64.zip && rm -f instantclient-basiclite-linuxx64.zip && \
    cd /opt/oracle/instantclient* && rm -f *jdbc* *occi* *mysql* *mql1* *ipc1* *jar uidrvci genezi adrci && \
    echo /opt/oracle/instantclient* > /etc/ld.so.conf.d/oracle-instantclient.conf && ldconfig && \
    cd /opt/oracle/ && mkdir wallet && \
    wget https://download.oracle.com/otn_software/linux/instantclient/instantclient-sqlplus-linuxx64.zip && \
    unzip instantclient-sqlplus-linuxx64.zip && rm -f instantclient-sqlplus-linuxx64.zip

# Install packages, build and keep only prod packages
WORKDIR /app
COPY . ./
RUN cd /opt/oracle/instantclient*/network/admin && cp /app/src/sqlnet.ora .
RUN npm ci --only=prod && \
    npm run build

# # Deployment container
# FROM registry.access.redhat.com/ubi8/ubi-micro

# Set node to production 
ENV NODE_ENV production

# # Node packages and dependencies
# COPY --from=builder /usr/bin/node /usr/bin/
# COPY --from=builder /usr/lib64/libz.so.1 /usr/lib64/
# COPY --from=builder /usr/lib64/libbrotlidec.so.1 /usr/lib64/
# COPY --from=builder /usr/lib64/libbrotlienc.so.1 /usr/lib64/
# COPY --from=builder /usr/lib64/libcrypto.so.1.1 /usr/lib64/
# COPY --from=builder /usr/lib64/libssl.so.1.1 /usr/lib64/
# COPY --from=builder /usr/lib64/libstdc++.so.6 /usr/lib64/
# COPY --from=builder /usr/lib64/libgcc_s.so.1 /usr/lib64/
# COPY --from=builder /usr/lib64/libbrotlicommon.so.1 /usr/lib64/

# # Copy over app
# WORKDIR /app
# COPY --from=builder /app/node_modules ./node_modules
# COPY --from=builder /app/dist ./dist

# Expose port - mostly a convention, for readability
EXPOSE 3000

# Start up command
ENTRYPOINT ["node", "dist/main"]
