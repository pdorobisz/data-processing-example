#!/bin/sh

if [ $# -ne 1 ]; then
  echo "Usage: $0 TOPIC"
  exit 1
fi

KAFKA_CONTAINER_NAME=dataprocessingexample_kafka_1
KAFKA_CONTAINER_ID=`docker ps -f "name=$KAFKA_CONTAINER_NAME" -q`
echo connecting to $KAFKA_CONTAINER_ID container...
docker exec $KAFKA_CONTAINER_ID /bin/sh -c "\$KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server \$HOSTNAME:9092 --topic $1 --from-beginning"
