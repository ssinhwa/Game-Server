package com.ssinhwa.gameserver.redisserver.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto implements Serializable {
    private String roomId;
    private String writer;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}
