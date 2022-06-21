package com.ssinhwa.gameserver;

import com.ssinhwa.gameserver.chatserver.dto.MessageDto;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.BlockingQueue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSocketTest {

    @LocalServerPort
    private int port;

    private BlockingQueue<MessageDto> messages;
}
