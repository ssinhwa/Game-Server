package com.ssinhwa.gameserver.redisserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodicData {
    private Integer i;
    private Integer q;
    private List<Integer> p;
}
