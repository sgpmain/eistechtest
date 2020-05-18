#start docker-compose with cassandra, kafka, zookeeper
docker-compose -f c:\sgp\jpro\eistechtest\docker-compose-eistechtest--env.yml up

#make sure smth. <install-dir>/kafka/bin/windows is in path or go to dir
kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic word-kafka-topic-name
kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic sentence-kafka-topic-name

# check topics have been created
kafka-topics.bat --list --bootstrap-server localhost:9092

kafka-console-consumer --bootstrap-server localhost:9092  --topic word-kafka-topic-name
kafka-console-consumer --bootstrap-server localhost:9092  --topic sentence-kafka-topic-name

#####################################
# cassandra
cqlsh> create keyspace test with replication = {'class':'SimpleStrategy', 'replication_factor':3};
cqlsh> use test;
cqlsh:test> create table book ( id int primary key, sentence text );
