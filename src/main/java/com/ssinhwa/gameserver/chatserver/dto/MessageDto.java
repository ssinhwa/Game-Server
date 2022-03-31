package com.ssinhwa.gameserver.chatserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String roomId;
    private String writer;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}
