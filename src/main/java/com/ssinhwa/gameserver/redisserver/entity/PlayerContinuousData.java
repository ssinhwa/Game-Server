package com.ssinhwa.gameserver.redisserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerContinuousData {
    private Integer i; // player Id
    private Integer s; // State
    private WPosition p;    // Position
    private double r;   // Rotation
}
