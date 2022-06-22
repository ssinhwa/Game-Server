package com.ssinhwa.gameserver.redisserver.controller;

import com.ssinhwa.gameserver.redisserver.dto.ChatRoomDto;
import com.ssinhwa.gameserver.redisserver.service.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
@Slf4j
public class ChatRoomController {
    private final ChatServiceImpl chatService;

    @GetMapping("/room")
    public void getRoom(String roomId, Model model) {
        log.info("Chat Room, roomID : " + roomId);
        model.addAttribute("room", chatService.findChatRoomByRoomId(roomId));
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoomDto> getRoomList() {
        return chatService.findAll();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoomDto createChatRoom(@RequestParam String name) {
        ChatRoomDto chatRoom = chatService.createChatRoom(name);
        log.info("Controller -> chatRoom.getRoomId() = " + chatRoom.getRoomId());
        return chatRoom;
    }

    @GetMapping("/room/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        ChatRoomDto chatRoomDto = chatService.findChatRoomByRoomId(roomId);
        return chatRoomDto.getRoomName();
    }

}
