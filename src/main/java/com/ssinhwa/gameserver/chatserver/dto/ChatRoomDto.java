package com.ssinhwa.gameserver.chatserver.dto;

import com.ssinhwa.gameserver.chatserver.service.ChatService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Slf4j
public class ChatRoomDto {
    private String roomId;
    private String name;

    // 채팅방에 입장한 클라이언트들 정보
    private Set<WebSocketSession> sessions = new HashSet<>();

    public ChatRoomDto(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, MessageDto messageDto, ChatService chatService) {
        sessions.add(session);  // 채팅 룸 목록에 저장
        log.info("세션 : " + session);
        sendMessage(messageDto, chatService);
    }

    public void sendMessage(MessageDto messageDto, ChatService chatService) {
        log.info("Dto 에서 메세지 보냄");
        sessions.
                parallelStream().
                forEach(session -> chatService.sendMessage(session, messageDto));
    }
}
