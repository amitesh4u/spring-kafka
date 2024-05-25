package com.amitesh.springbootkafka.controller;

import com.amitesh.springbootkafka.kafka.JsonKafkaProducer;
import com.amitesh.springbootkafka.payload.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/kafka")
public class KafkaControllerV2 {

  private final JsonKafkaProducer kafkaProducer;

  public KafkaControllerV2(JsonKafkaProducer jsonkafkaProducer) {
    this.kafkaProducer = jsonkafkaProducer;
  }

  @PostMapping("/publish/{topic}")
  public ResponseEntity<String> produce(@PathVariable String topic,
      @RequestParam String sender, @RequestParam String receiver,
      @RequestParam("message") String message) {
    kafkaProducer.sendMessage(topic, new Message(sender, receiver, message));
    return ResponseEntity.ok("Message Received");
  }
}
