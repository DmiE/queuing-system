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
# Define default command.
CMD ["mongod"]

# Expose ports.
#   - 27017: process
#   - 28017: http
EXPOSE 27017
EXPOSE 28017
