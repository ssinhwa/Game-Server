package com.ssinhwa.gameserver.chatserver.config;

//
//@EnableKafka
//@Configuration
//public class KafkaConsumerConfig {
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, MessageDto> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, MessageDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<String, MessageDto> consumerFactory() {
//        JsonDeserializer<MessageDto> deserializer = new JsonDeserializer<>(MessageDto.class);
//        deserializer.setRemoveTypeHeaders(false);
//        deserializer.addTrustedPackages("*");
//        deserializer.setUseTypeMapperForKey(true);
//
//        ImmutableMap<String, Object> config = ImmutableMap.<String, Object>builder()
//                .put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER)
//                .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
//                .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer)
//                .put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest")
//                .put("group.id", KafkaConstants.GROUP_ID)
//                .build();
//
//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
//    }
//
//}
