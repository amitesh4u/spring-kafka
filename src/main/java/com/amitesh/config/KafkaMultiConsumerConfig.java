package com.amitesh.config;

import com.amitesh.payload.EmailMessage;
import com.amitesh.payload.Message;
import com.amitesh.payload.SmsMessage;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaMultiConsumerConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

//  @Value(value = "${kafka.consumer.multi.group-id")
//  private String groupId;

  @Value(value = "${spring.kafka.consumer.auto-offset-reset}")
  private String autoOffsetReset;

  @Bean
  public RecordMessageConverter multiTypeConverter() {
    /* To be able to deserialize the incoming message to correct object,
       we’ll need to provide our Consumer with a custom MessageConverter.
    */
    StringJsonMessageConverter converter = new StringJsonMessageConverter();

    DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
    /* By default, the mapper infers the type of the received objects.
       We need to tell it explicitly to use the type header to determine the target class for deserialization
    */
    typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);

    /* Configure the packages trusted by the mapper */
    //typeMapper.addTrustedPackages("com.amitesh.payload");

    /* Provide the reverse mapping information */
    Map<String, Class<?>> mappings = new HashMap<>();
    mappings.put("sms", SmsMessage.class);
    mappings.put("email", EmailMessage.class);
    mappings.put("message", Message.class);

    typeMapper.setIdClassMapping(mappings);

    converter.setTypeMapper(typeMapper);

    return converter;
  }

  @Bean
  public ConsumerFactory<String, Object> multiTypeConsumerFactory() {
    HashMap<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    return new DefaultKafkaConsumerFactory<>(props);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> multiTypeKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(multiTypeConsumerFactory());
    factory.setRecordMessageConverter(multiTypeConverter());
    return factory;
  }

}
