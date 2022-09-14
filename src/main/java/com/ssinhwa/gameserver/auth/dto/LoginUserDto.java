package com.ssinhwa.gameserver.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginUserDto {
    private String token;
    private Long playerId;
}
