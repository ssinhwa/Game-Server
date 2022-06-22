package com.ssinhwa.gameserver.redisserver.repository;

import com.ssinhwa.gameserver.redisserver.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    public ChatRoom findChatRoomByRoomId(String roomId);
}
