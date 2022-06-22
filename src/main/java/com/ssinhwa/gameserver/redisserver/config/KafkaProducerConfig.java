package com.ssinhwa.gameserver.redisserver.config;

//@Configuration
//@EnableKafka
//public class KafkaProducerConfig {
//
//    @Bean
//    public ProducerFactory<String, MessageDto> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(kafkaProducerConfiguration());
//    }
//
//    @Bean
//    public Map<String, Object> kafkaProducerConfiguration() {
//        return ImmutableMap.<String, Object>builder()
//                .put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER)
//                .put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
//                .put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class)
//                .put("group.id", KafkaConstants.GROUP_ID)
//                .build();
//    }
//
//    @Bean
//    public KafkaTemplate<String, MessageDto> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//}
