package com.amitesh.springbootkafka.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

  private final KafkaTemplate<String, String> kafkaTemplate;

  public JsonKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(final String topic, final String message){
    LOGGER.info("Message {} sent to Topic {}", message, topic);
    kafkaTemplate.send(topic, message);
  }
}
