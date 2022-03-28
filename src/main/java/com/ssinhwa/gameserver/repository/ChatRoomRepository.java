package com.ssinhwa.gameserver.repository;

import com.ssinhwa.gameserver.dto.ChatRoomDTO;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoomDTO> map;

    @PostConstruct
    private void init() {
        map = new LinkedHashMap<>();
    }

    public List<ChatRoomDTO> findAll() {
        List<ChatRoomDTO> result = new ArrayList<>(map.values());
        Collections.reverse(result);
        return result;
    }

    public ChatRoomDTO findById(String id) {
        return map.get(id);
    }

    public ChatRoomDTO createChatRoom(String name) {
        ChatRoomDTO newChatRoom = new ChatRoomDTO(name);
        map.put(name, newChatRoom);
        return newChatRoom;
    }
}
