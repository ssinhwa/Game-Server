package com.ssinhwa.gameserver.redisserver.service;

import com.ssinhwa.gameserver.redisserver.dto.ChatRoomDto;
import com.ssinhwa.gameserver.redisserver.dto.MessageDto;

import java.util.List;

public interface ChatService {
    public List<ChatRoomDto> findAll();

    public ChatRoomDto findChatRoomByRoomId(String roomId);

    public ChatRoomDto createChatRoom(String name);

    public void saveMessage(MessageDto message);
}
