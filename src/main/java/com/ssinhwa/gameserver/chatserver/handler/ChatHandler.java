package com.ssinhwa.gameserver.chatserver.handler;

/*

STOMP 에서는 필요 없으므로 삭제.

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {
    private final ChatService chatService;
    private final ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("핸들러입니다");
        log.info("PayLoad : " + payload);
        // session.sendMessage(new TextMessage("Welcome"));
        MessageDto messageDto = objectMapper.readValue(payload, MessageDto.class);
        ChatRoomDto room = chatService.findChatRoomDtoById(messageDto.getRoomId());
        room.handleActions(session, messageDto, chatService);
    }
}

// 1. Controller 에서 요청을 받으면 함수 호출. 우리는 ChatController -> Service 로 가게 된다.
// 2. 오브젝트 Mapper 가 Payload 를 읽어서 MessageDto 로 변환.
// 3. ChatRoomDto 에서 세션별로 메세지 보낸다.
// 4. 최종적으로 ChatService 에서 Session class 통해서 메세지 보냄.

// 출처 : https://daddyprogrammer.org/post/4691/spring-websocket-chatting-server-stomp-server/

 */