package com.amitesh.springbootkafka;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaRecordReader {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaRecordReader.class);

  private static final List<String> IGNORED_TOPICS = List.of("__consumer_offsets" ,"kafka-demo-topic", "sample", "connect-test");

  public static void main(String[] args) {
    Properties consumerProperties = getProperties();

    try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties)) {
      Map<String, List<PartitionInfo>> stringListMap = consumer.listTopics(Duration.ofSeconds(3));
      for (Entry<String, List<PartitionInfo>> entry : stringListMap.entrySet()) {
        String topic = entry.getKey();
        if (!IGNORED_TOPICS.contains(topic)) { // Ignore internal Topic
          List<Integer> partitions = entry.getValue().stream().map(PartitionInfo::partition)
              .toList();
          LOGGER.info("Topic: " + topic + " | Partitions: " + partitions);
          printTopicRecords(consumer, topic, partitions);
        }
      }
    }
  }

  private static void printTopicRecords(final KafkaConsumer<String,String> consumer,
      final String topic, final List<Integer> partitionNums) {
    List<TopicPartition> partitions = new ArrayList<>();//List.of(partition);
    partitionNums.forEach(partitionNum1 -> partitions.add(new TopicPartition(topic, partitionNum1)));
    consumer.assign(partitions);

    /* Update the commented code to fetch last N messages for each Topic Partition i.e. Run in a loop for each partition */
//    int messagesToRetrieve = 5;
//    consumer.seekToEnd(partitions); // move the partition to end
//    /* position() -> get the index of partition current position */
//    long startIndex = consumer.position(partition) - messagesToRetrieve;
//    consumer.seek(partition, startIndex)

    consumer.seekToBeginning(partitions);

    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));

    for (ConsumerRecord<String, String> record : records) {
      LOGGER.info(String.valueOf(record));
    }
  }

  private static Properties getProperties() {
    Properties consumerProperties = new Properties();
    consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class.getName());
    consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class.getName());
    consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "testId");
    consumerProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer-testId-3");
    return consumerProperties;
  }
}
