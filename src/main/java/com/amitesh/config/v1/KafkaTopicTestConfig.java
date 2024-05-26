package com.amitesh.config.v1;


import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaTopicTestConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTopicTestConfig.class);

  /**
   * The NewTopic bean causes the topic to be created on the broker; it is not needed if the topic
   * already exists.
   *
   * @return NewTopic
   */
  @Bean
  public NewTopic testTopic1() {
    return TopicBuilder.name("test-topic1")
        .partitions(10)
        .replicas(1)
        .build();
  }

  /**
   * The NewTopic bean causes the topic to be created on the broker; it is not needed if the topic
   * already exists.
   *
   * @return NewTopic
   */
  @Bean
  public NewTopic testTopic2() {
    return TopicBuilder.name("test-topic2")
        .partitions(10)
        .replicas(1)
        .build();
  }

  /* Consumer for Topic1 and Topic 2. Runs during Startup */
  @KafkaListener(id = "testIdStartup", topics = {"test-topic1", "test-topic2"})
  public void listen(String message) {
    LOGGER.info("Message {} received through Startup Test listener", message);
  }

  /* Producer for Topics. Runs during startup */
  @Bean
  public ApplicationRunner runner(KafkaTemplate<String, String> template) {
    return args -> {
      template.send("test-topic1", "test1");
      template.send("test-topic2", "test2");
    };
  }

}
