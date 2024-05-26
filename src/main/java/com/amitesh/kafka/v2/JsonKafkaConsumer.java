package com.amitesh.kafka.v2;

import com.amitesh.payload.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

  @KafkaListener(id = "${kafka.consumer.json.group-id", topics = "message-topic",
      containerFactory = "jsonMessageKafkaListenerContainerFactory")
  public void consumeMessageWithConsumerRecord(final ConsumerRecord<String, Message> record, final Message message) {
    LOGGER.info("Consumer Record Message {} received from Topic {} | Partition {} | Offset {} at {}",
        message, record.topic(), record.partition(), record.offset(), record.timestamp());
  }

}
