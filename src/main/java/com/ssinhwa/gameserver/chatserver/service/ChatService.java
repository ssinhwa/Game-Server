package com.ssinhwa.gameserver.chatserver.service;

/*
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
        ChatRoomDto newChatRoom = new ChatRoomDto(name);
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
*/