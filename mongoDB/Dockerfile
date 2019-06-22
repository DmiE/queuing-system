FROM mongo

WORKDIR /data

COPY dump.js .
COPY entrypoint.sh /entrypoint.sh

RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]

EXPOSE 27017
EXPOSE 28017

