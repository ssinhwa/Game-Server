package com.ssinhwa.gameserver.chatserver.service;

//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class KafkaConsumer {
//
//    private final SimpMessagingTemplate template;
//
//    @KafkaListener(topics = KafkaConstants.KAFKA_TOPIC, groupId = KafkaConstants.GROUP_ID)
//    public void consume(MessageDto message) throws IOException {
//        log.info("Consumed Message : " + message.getMessage());
//        HashMap<String, String> msg = new HashMap<>();
//        msg.put("roomId", message.getRoomId());
//        msg.put("message", message.getMessage());
//        msg.put("writer", message.getWriter());
//
//        ObjectMapper mapper = new ObjectMapper();
//        template.convertAndSend("/topic/tt", mapper.writeValueAsString(msg));
//    }
//}
