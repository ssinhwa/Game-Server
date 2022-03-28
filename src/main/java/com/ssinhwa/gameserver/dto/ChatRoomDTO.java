package com.ssinhwa.gameserver.dto;

import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class ChatRoomDTO {

    private String roomId;
    private String roomName;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public ChatRoomDTO(String name) {
        this.roomId = UUID.randomUUID().toString();
        this.roomName = name;
    }
}
