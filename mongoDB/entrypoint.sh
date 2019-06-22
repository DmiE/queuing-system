#!/bin/bash

/usr/bin/mongod --bind_ip 0.0.0.0 &

sleep 10
mongo admin --host 127.0.0.1 --port 27017  < /data/dump.js

while true;
 do sleep 12 ;
done
