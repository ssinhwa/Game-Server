package com.ssinhwa.gameserver.controller;


import com.ssinhwa.gameserver.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotypf:qe.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template;

    // "/test/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(MessageDTO message) {
        message.changeMessage(message.getWriter() + "님이 채팅방에 입장하셨습니다.");
        template.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

    // "/test/chat/message"
    @MessageMapping(value = "/chat/message")
    public void message(MessageDTO message) {
        template.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }


}

// https://dev-gorany.tistory.com/235