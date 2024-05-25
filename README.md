# Spring Boot 3.3 and Kafka 3.7 application

## Version details
### a) Version 1 contains code using _String Serializer_
It has sample consumers with
* Only Message
* Message with Header
* Consumer Record
* Message from specific Partition
* Message from specific Partition with offset
* Message after filter

There is additional code in _KafkaTopicConfig_ class to produce and consume
test data during application startup



### Note:
* To fetch all existing records from each Topic please run _KafkaRecordReader_ class
* Use _sample-requests.http_ for direct testing

## Intellij Plugin
* Kafka
* HTTP