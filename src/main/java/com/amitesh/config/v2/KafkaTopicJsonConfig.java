package com.amitesh.config.v2;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicJsonConfig {

  @Bean
  public NewTopic messageTopic() {
    return TopicBuilder.name("message-topic")
        .partitions(4)
        .replicas(1)
        .build();
  }
}
