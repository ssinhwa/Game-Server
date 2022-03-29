package com.ssinhwa.gameserver.chatserver.controller;

import com.ssinhwa.gameserver.chatserver.dto.ChatRoomDto;
import com.ssinhwa.gameserver.chatserver.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoomDto createRoom(@RequestParam String name) {
        return chatService.createChatRoomDto(name);
    }

    @GetMapping
    public List<ChatRoomDto> findAllRoom() {
        return chatService.findAllRoom();
    }
}
