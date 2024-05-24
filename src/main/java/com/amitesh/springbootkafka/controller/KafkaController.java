package com.amitesh.springbootkafka.controller;

import com.amitesh.springbootkafka.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/kafka")
public class KafkaController {

  private final KafkaProducer kafkaProducer;

  public KafkaController(KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  @PostMapping("/publish/{topic}")
  public ResponseEntity<String> produce(@PathVariable String topic,
      @RequestParam(name = "key", required = false) Integer key,
      @RequestParam("message") String message) {
    kafkaProducer.sendMessage(topic, key, message);
    return ResponseEntity.ok("Message Received");
  }
}
