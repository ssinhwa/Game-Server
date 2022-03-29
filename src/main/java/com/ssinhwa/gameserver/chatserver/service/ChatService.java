package com.ssinhwa.gameserver.chatserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssinhwa.gameserver.chatserver.dto.ChatRoomDto;
import com.ssinhwa.gameserver.chatserver.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ObjectMapper objectMapper;
    private final Map<String, ChatRoomDto> chatRooms;

    public List<ChatRoomDto> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomDto findChatRoomDtoById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoomDto createChatRoomDto(String name) {
        String roomId = UUID.randomUUID().toString();
        ChatRoomDto newChatRoom = new ChatRoomDto(roomId, name);
        chatRooms.put(roomId, newChatRoom);
        return newChatRoom;
    }

    public void sendMessage(WebSocketSession session, MessageDto messageDto) {
        try {
            log.info("서비스에서 메세지 보냄");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageDto)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
