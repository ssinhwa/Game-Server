package com.ssinhwa.gameserver.chatserver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssinhwa.gameserver.chatserver.dto.ChatRoomDto;
import com.ssinhwa.gameserver.chatserver.dto.MessageDto;
import com.ssinhwa.gameserver.chatserver.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

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

// 1. 오브젝트 Mapper 가 Payload 를 읽어서 MessageDto 로 변환.
// 2. ChatRoomDto 에서 세션별로 메세지 보낸다.
// 3. 최종적으로 ChatService 에서 Session class 통해서 메세지 보냄.

// 출처 : https://daddyprogrammer.org/post/4691/spring-websocket-chatting-server-stomp-server/
