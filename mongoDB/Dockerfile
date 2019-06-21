#
# MongoDB Dockerfile
#
# https://github.com/dockerfile/mongodb
#

# Pull base image.
FROM mongo


# Define working directory.
WORKDIR /data
RUN sed -i "s/.*bindIp.*/bindIp: 0.0.0.0/" /etc/mongod.conf.orig
COPY dump.js /docker-entrypoint-initdb.d/
# Define default command.
CMD ["mongod"]

EXPOSE 27017
EXPOSE 28017
