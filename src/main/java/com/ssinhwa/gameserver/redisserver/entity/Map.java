package com.ssinhwa.gameserver.redisserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// 속도 TEST용
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Map {
    @Id
    @GeneratedValue
    private Long id;

    private Long fileId;

    @Lob
    @Column(length = 200000)
    private String value;
}
