package com.amitesh.springbootkafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class AppConfig {

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

  /* Consumer for Topic1 and Topic 2 */
  @KafkaListener(id = "testId", topics = {"test-topic1", "test-topic2"})
  public void listen(String in) {
    System.out.println(in);
  }

  /* Producer for Topic 1 */
  @Bean
  public ApplicationRunner runner1(KafkaTemplate<String, String> template) {
    return args -> template.send("test-topic1", "test1");
  }

  /* Producer for Topic 2 */
  @Bean
  public ApplicationRunner runner2(KafkaTemplate<String, String> template) {
    return args -> template.send("test-topic2", "test2");
  }

}
