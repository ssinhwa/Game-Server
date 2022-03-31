package com.ssinhwa.gameserver.chatserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class ChatRoom {
    @GeneratedValue
    @Id
    private Long id;
    private String roomId;
    private String roomName;

    public ChatRoom(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public String changeName(String roomName) {
        this.roomName = roomName;
        return roomName;
    }
}
