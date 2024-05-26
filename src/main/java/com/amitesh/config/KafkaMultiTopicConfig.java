package com.amitesh.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaMultiTopicConfig {

  @Bean
  public NewTopic multiMessageTopic() {
    return TopicBuilder.name("multi-message-topic")
        .partitions(4)
        .replicas(1)
        .build();
  }
}
