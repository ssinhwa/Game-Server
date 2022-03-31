package com.ssinhwa.gameserver.chatserver.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
public class ChatRoom {
    @GeneratedValue
    @Id
    private Long id;

    private String roomId;
    private String roomName;

    public void createChatRoom(String roomName) {
        this.roomId = UUID.randomUUID().toString();
        this.roomName = roomName;
    }

    public String changeName(String roomName) {
        this.roomName = roomName;
        return roomName;
    }
}
