package com.ssinhwa.gameserver.redisserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerContinuousData {
    private Integer playerId;
    private Integer playerState;
    private WPosition worldPosition;
    private Float rotation;
}
