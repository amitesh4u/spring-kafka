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

### b) Version 2 contains code using _Json Serializer_

### c) Version 3 contains code using _Json Serializer_ with multiple Object Type
* It uses RecordMessageConverter and KafkaHandler to handle different message type


### Note:
* To fetch all existing records from each Topic please run _KafkaRecordReader_ class
* Use _sample-requests.http_ for direct testing

## Intellij Plugin
* Kafka
* HTTP