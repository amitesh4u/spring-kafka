package com.amitesh.springbootkafka.controller;

import com.amitesh.springbootkafka.kafka.v1.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/kafka")
public class KafkaControllerV1 {

  private final KafkaProducer kafkaProducer;

  public KafkaControllerV1(KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  @PostMapping("/publish/{topic}")
  public ResponseEntity<String> produce(@PathVariable String topic,
      @RequestParam("message") String message) {
    kafkaProducer.sendMessage(topic, message);
    return ResponseEntity.ok("Message Received");
  }
}
