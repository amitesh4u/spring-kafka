package com.amitesh.springbootkafka.kafka;

import com.amitesh.springbootkafka.payload.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

  private final KafkaTemplate<String, Message> jsonMessageKafkaTemplate;

  public JsonKafkaProducer(KafkaTemplate<String, Message> jsonMessageKafkaTemplate) {
    this.jsonMessageKafkaTemplate = jsonMessageKafkaTemplate;
  }

  public void sendMessage(final String topic, final Message message){
    LOGGER.info("Message {} sent to Topic {}", message, topic);
    jsonMessageKafkaTemplate.send(topic, message);
  }
}
