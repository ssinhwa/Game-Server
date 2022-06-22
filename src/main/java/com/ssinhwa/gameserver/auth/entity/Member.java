package com.ssinhwa.gameserver.auth.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    private String playerName;

    @ColumnDefault("False")
    private boolean emailVerified;

    private String token;


    public Member(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
