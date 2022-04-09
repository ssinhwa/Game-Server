package com.ssinhwa.gameserver.main.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 300, nullable = false)
    private String password;
}
