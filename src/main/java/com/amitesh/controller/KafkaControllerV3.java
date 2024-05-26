package com.amitesh.controller;

import com.amitesh.kafka.MultiKafkaProducer;
import com.amitesh.payload.EmailMessage;
import com.amitesh.payload.Message;
import com.amitesh.payload.SmsMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v3/kafka/publish/{topic}")
public class KafkaControllerV3 {

  private final MultiKafkaProducer kafkaProducer;

  public KafkaControllerV3(MultiKafkaProducer multikafkaProducer) {
    this.kafkaProducer = multikafkaProducer;
  }

  @PostMapping("/message")
  public ResponseEntity<String> produceMessage(@PathVariable String topic,
      @RequestParam("message") String message) {
    kafkaProducer.sendMessage(topic, message);
    return ResponseEntity.ok("Message Received");
  }

  @PostMapping("/commonmessage")
  public ResponseEntity<String> produceMessage(@PathVariable String topic,
      @RequestParam("sender") String sender, @RequestParam("receiver") String receiver,
      @RequestParam("message") String message) {
    kafkaProducer.sendMessage(topic, new Message(sender, receiver, message));
    return ResponseEntity.ok("Message Received");
  }

  @PostMapping("/email")
  public ResponseEntity<String> produceEmail(@PathVariable String topic,
      @RequestParam("fromEmail") String fromEmail, @RequestParam("toEmail") String toEmail,
      @RequestParam("subject") String subject, @RequestParam("body") String body) {
    kafkaProducer.sendMessage(topic, new EmailMessage(fromEmail, toEmail, subject, body));
    return ResponseEntity.ok("Message Received");
  }

  @PostMapping("/sms")
  public ResponseEntity<String> produceSMS(@PathVariable String topic,
      @RequestParam("fromNumber") String fromNum, @RequestParam("toNumber") String toNum,
      @RequestParam("message") String message) {
    kafkaProducer.sendMessage(topic, new SmsMessage(fromNum, toNum, message));
    return ResponseEntity.ok("Message Received");
  }

}
