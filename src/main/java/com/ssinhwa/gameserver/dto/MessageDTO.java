package com.ssinhwa.gameserver.dto;

import lombok.Getter;

@Getter
public class MessageDTO {
    private String roomId;
    private String writer;
    private String message;

    public void changeMessage(String message) {
        this.message = message;
    }

}
