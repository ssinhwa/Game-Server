package com.ssinhwa.gameserver.chatserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String roomId;
    private String sender;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}
