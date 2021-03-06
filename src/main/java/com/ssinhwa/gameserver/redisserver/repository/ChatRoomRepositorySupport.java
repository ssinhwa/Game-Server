package com.ssinhwa.gameserver.redisserver.repository;

import com.ssinhwa.gameserver.redisserver.config.RedisConstants;
import com.ssinhwa.gameserver.redisserver.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ChatRoomRepositorySupport {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom(String name) {
        log.info("Create Chat Room");
        String roomId = UUID.randomUUID().toString();
        ChatRoom newChatRoom = new ChatRoom(roomId, name);
        chatRoomRepository.save(newChatRoom);
        return newChatRoom;
    }

    public void enterChatRoom(String roomId) {
        ChannelTopic channelTopic = new ChannelTopic(RedisConstants.REDIS_TOPIC);
    }

    /*
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
    */
}
