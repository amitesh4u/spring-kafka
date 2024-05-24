package com.amitesh.springbootkafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
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
    LOGGER.info("Header Message {} received from Topic {} | Partition {} | Key {} at {}", message, topic,
        partition, key, ts);
  }

  @KafkaListener(id = "testIdConsumerRecord", topics = {"test-topic1", "test-topic2"})
  public void consumeMessageWithConsumerRecord(final ConsumerRecord<String, String> record) {
    LOGGER.info("Consumer Record Message {} received from Topic {} | Partition {} | Key {} at {}",
        record.value(), record.topic(), record.partition(), record.key(), record.timestamp());
  }
}
