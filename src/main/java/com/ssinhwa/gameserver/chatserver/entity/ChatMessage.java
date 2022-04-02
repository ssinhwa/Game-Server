package com.ssinhwa.gameserver.chatserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class ChatMessage {
    @GeneratedValue
    @Id
    private Long id;

    private String roomId;
    private String writer;
    private String message;

    public ChatMessage(String roomId, String writer, String message) {
        this.roomId = roomId;
        this.writer = writer;
        this.message = message;
    }

    public void changeMessage(String message) {
        this.message = message;
    }
}
