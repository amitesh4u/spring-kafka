package com.amitesh.kafka;

import com.amitesh.payload.EmailMessage;
import com.amitesh.payload.Message;
import com.amitesh.payload.SmsMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(id = "${kafka.consumer.multi.group-id}", topics = "multi-message-topic")
public class MultiKafkaConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(MultiKafkaConsumer.class);

  @KafkaHandler
  public void consumeEmailMessageWithConsumerRecord(final ConsumerRecord<String, Object> record, final EmailMessage email) {
    LOGGER.info("Consumer Record Email {} received from Topic {} | Partition {} | Offset {} at {}",
        email, record.topic(), record.partition(), record.offset(), record.timestamp());
  }

  @KafkaHandler
  public void consumeSMSMessageWithConsumerRecord(final ConsumerRecord<String, Object> record, final SmsMessage sms) {
    LOGGER.info("Consumer Record SMS {} received from Topic {} | Partition {} | Offset {} at {}",
        sms, record.topic(), record.partition(), record.offset(), record.timestamp());
  }

  @KafkaHandler
  public void consumeMessageWithConsumerRecord(final ConsumerRecord<String, Object> record, final Message message) {
    LOGGER.info("Consumer Record Common Message {} received from Topic {} | Partition {} | Offset {} at {}",
        message, record.topic(), record.partition(), record.offset(), record.timestamp());
  }

  @KafkaHandler(isDefault = true)
  public void consumeObjectWithConsumerRecord(final ConsumerRecord<String, Object> record, final Object message) {
    LOGGER.info("Consumer Record Object {} received from Topic {} | Partition {} | Offset {} at {}",
        message, record.topic(), record.partition(), record.offset(), record.timestamp());
  }

}
