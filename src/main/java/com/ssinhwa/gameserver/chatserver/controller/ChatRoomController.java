package com.ssinhwa.gameserver.chatserver.controller;

import com.ssinhwa.gameserver.chatserver.dto.ChatRoomDto;
import com.ssinhwa.gameserver.chatserver.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoomDto> getRoomList() {
        return chatService.findAll();
    }

    // 채팅방 생성
    @PostMapping("/room")
    public ChatRoomDto createChatRoom(@RequestParam String name) {
        return chatService.createChatRoom(name);
    }

    // Room Id 에 맞는 채팅방 조회
    @GetMapping("/room/{roomId}")
    public ChatRoomDto roomInfo(@PathVariable String roomId) {
        return chatService.findChatRoomByRoomId(roomId);
    }


}
