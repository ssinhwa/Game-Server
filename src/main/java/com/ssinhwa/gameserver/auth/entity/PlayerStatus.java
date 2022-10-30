package com.ssinhwa.gameserver.auth.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PlayerStatus {

    @Id
    @GeneratedValue
    @Column(name = "status_id")
    private Long id;

    private int fullHp;

    private int nowHp;

    private int weaponName;

    private int nowLevel;

    @OneToOne(mappedBy = "playerStatus")
    private Member member;

}
