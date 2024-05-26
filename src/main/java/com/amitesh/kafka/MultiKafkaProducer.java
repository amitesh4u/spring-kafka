package com.amitesh.kafka;

import com.amitesh.payload.EmailMessage;
import com.amitesh.payload.Message;
import com.amitesh.payload.SmsMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MultiKafkaProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(MultiKafkaProducer.class);

  private final KafkaTemplate<String, Object> multiTypeKafkaTemplate;

  public MultiKafkaProducer(KafkaTemplate<String, Object> multiTypeKafkaTemplate) {
    this.multiTypeKafkaTemplate = multiTypeKafkaTemplate;
  }

  public void sendMessage(final String topic, final String message) {
    LOGGER.info("String Message {} sent to Topic {}", message, topic);
    multiTypeKafkaTemplate.send(topic, message);
  }

  public void sendMessage(final String topic, final Message message) {
    LOGGER.info("Message {} sent to Topic {}", message, topic);
    multiTypeKafkaTemplate.send(topic, message);
  }

  public void sendMessage(final String topic, final EmailMessage email) {
    LOGGER.info("Email {} sent to Topic {}", email, topic);
    multiTypeKafkaTemplate.send(topic, email);
  }

  public void sendMessage(final String topic, final SmsMessage sms) {
    LOGGER.info("SmsMessage {} sent to Topic {}", sms, topic);
    multiTypeKafkaTemplate.send(topic, sms);
  }
}
