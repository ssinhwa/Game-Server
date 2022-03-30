package com.ssinhwa.gameserver.chatserver.dto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Getter
@Slf4j
public class ChatRoomDto {
    private String roomId;
    private String name;

    // 채팅방에 입장한 클라이언트들 정보
    // private Set<WebSocketSession> sessions = new HashSet<>();

    public ChatRoomDto(String name) {
        String randomId = UUID.randomUUID().toString();
        this.roomId = randomId;
        this.name = name;
    }

    /*
    // Pub/Sub 에서는 구독자 관리가 알아서 되므로 세션 관리 필요 x
    public void handleActions(WebSocketSession session, MessageDto messageDto, ChatService chatService) {
        sessions.add(session);  // 채팅 룸 목록에 저장
        log.info("세션 : " + session);
        sendMessage(messageDto, chatService);
    }

     */
    /*
    발송도 pub / sub 에서 알아서 해준다.

    public void sendMessage(MessageDto messageDto, ChatService chatService) {
        log.info("Dto 에서 메세지 보냄");
        sessions.
                parallelStream().
                forEach(session -> chatService.sendMessage(session, messageDto));
    }


     */
}
