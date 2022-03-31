package com.ssinhwa.gameserver.chatserver.service;

import com.ssinhwa.gameserver.chatserver.dto.ChatRoomDto;

import java.util.List;

public interface ChatService {
    public List<ChatRoomDto> findAll();

    public ChatRoomDto findChatRoomById(Long id);

    public ChatRoomDto findChatRoomByRoomId(String roomId);

    public ChatRoomDto createChatRoom(String name);
}
