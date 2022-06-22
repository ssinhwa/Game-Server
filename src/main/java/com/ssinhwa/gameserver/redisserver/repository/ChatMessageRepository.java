package com.ssinhwa.gameserver.redisserver.repository;

import com.ssinhwa.gameserver.redisserver.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    public ChatMessage findChatMessageByRoomId(String roomId);

    public ChatMessage findChatMessageById(Long id);
}
