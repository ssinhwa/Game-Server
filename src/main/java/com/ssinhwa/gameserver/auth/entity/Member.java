package com.ssinhwa.gameserver.auth.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

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

    @OneToOne
    @JoinColumn(name = "status_id")
    private PlayerStatus playerStatus;

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
