package com.amitesh.springbootkafka.kafka.v1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);


  /**
   * Consumer for given topics
   *
   * @param message Message sent by Producer
   */
  @KafkaListener(id = "testId", topics = {"test-topic1", "test-topic2"})
  public void consumeMessage(final String message) {
    LOGGER.info("Normal Message {} received", message);
  }

  @KafkaListener(id = "testIdHeader", topics = {"test-topic1", "test-topic2"})
  public void consumeMessageWithHeader(final @Payload String message,
      @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) Integer key,
      @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
      @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
    LOGGER.info("Header Message {} received from Topic {} | Partition {} | Key {} at {}", message,
        topic,
        partition, key, ts);
  }

  @KafkaListener(id = "testIdConsumerRecord", topics = {"test-topic1", "test-topic2"})
  public void consumeMessageWithConsumerRecord(final ConsumerRecord<String, String> record) {
    LOGGER.info("Consumer Record Message {} received from Topic {} | Partition {} | Key {} at {}",
        record.value(), record.topic(), record.partition(), record.key(), record.timestamp());
  }

  /**
   * Fetch all messages from beginning from given Partitions of the Topic
   * @param message message to consume
   * @param partition partition of stored message
   */
  @KafkaListener(id = "testIdPartition", topicPartitions = @TopicPartition(topic = "test-topic1", partitions = {"0", "2", "5"}))
  public void listenToPartition(
      @Payload String message,
      @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
    LOGGER.info("Partition Message {} received from Topic {} | Partition {} ", message, "test-topic1",
        partition);
  }

  /**
   * Fetch all messages from the given offset of the specific given Partitions of the Topic
   * @param message message to consume
   * @param partition partition of stored message
   */
  @KafkaListener(id = "testIdOffsetPartition",
      topicPartitions = @TopicPartition(topic = "test-topic1",
          partitionOffsets = {
              @PartitionOffset(partition = "0", initialOffset = "8"),
              @PartitionOffset(partition = "1", initialOffset = "1")}))
  public void listenToPartitionWithOffset(
      @Payload String message,
      @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
    LOGGER.info("Partition with offset Message {} received from Topic {} | Partition {} ", message, "test-topic1",
        partition);
  }

  @KafkaListener(id = "testIdFilter",
      topics = {"test-topic1", "test-topic2"},
      containerFactory = "filterKafkaListenerContainerFactory")
  public void listenWithFilter(@Payload String message,
      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
      @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
    LOGGER.info("Filter (not containing 'test') message {} received from Topic {} | Partition {} ", message, topic,
        partition);
  }
}
