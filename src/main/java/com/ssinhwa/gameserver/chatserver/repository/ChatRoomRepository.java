package com.ssinhwa.gameserver.chatserver.repository;

import com.ssinhwa.gameserver.chatserver.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    public ChatRoom createChatRoom(String name);

    public ChatRoom findChatRoomById(Long id);

    public ChatRoom findChatRoomByRoomId(String roomId);
}
