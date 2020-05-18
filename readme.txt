#start docker-compose with cassandra, kafka, zookeeper
docker-compose -f c:\sgp\jpro\eistechtest\docker-compose-eistechtest--env.yml up

#make sure smth. <install-dir>/kafka/bin/windows is in path or go to dir
kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic word-kafka-topic-name
kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic sentence-kafka-topic-name

# check topics have been created
kafka-topics.bat --list --bootstrap-server localhost:9092
