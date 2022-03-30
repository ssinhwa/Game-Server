package com.ssinhwa.gameserver.chatserver.repository;

import com.ssinhwa.gameserver.chatserver.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ChatRoomRepository {
    private final Map<String, ChatRoomDto> chatRoomDtoMap;

    public List<ChatRoomDto> findAllRoom() {
        log.info("Repository -> findAllRoom");
        ArrayList<ChatRoomDto> roomDtoArrayList = new ArrayList<>(chatRoomDtoMap.values());
        Collections.reverse(roomDtoArrayList);
        return roomDtoArrayList;
    }

    public ChatRoomDto findRoomById(String roomId) {
        log.info("Repository -> findRoomById");
        return chatRoomDtoMap.get(roomId);
    }

    public ChatRoomDto createChatRoom(String name) {
        log.info("Repository -> Create Chat Room");
        ChatRoomDto newChatRoom = new ChatRoomDto(name);
        chatRoomDtoMap.put(newChatRoom.getRoomId(), newChatRoom);
        return newChatRoom;
    }
}
