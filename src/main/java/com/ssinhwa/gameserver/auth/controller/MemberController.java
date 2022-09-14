package com.ssinhwa.gameserver.auth.controller;

import com.ssinhwa.gameserver.auth.dto.LoginDto;
import com.ssinhwa.gameserver.auth.dto.LoginUserDto;
import com.ssinhwa.gameserver.auth.dto.UserDto;
import com.ssinhwa.gameserver.auth.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberServiceImpl memberService;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserDto userDto) {
        try {
            memberService.signUp(userDto);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Sign Up Success";
    }

    @GetMapping("confirm-email")
    public String confirmEmail(@RequestParam String token) {
        memberService.confirmEmail(token);
        return "이메일 인증 완료!";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        String token = "";
        Long playerId = 0L;
        try {
            LoginUserDto user = memberService.login(loginDto);
            token = user.getToken();
            playerId = user.getPlayerId();
        } catch (Exception e) {
            return e.getMessage();
        }
        return token + "," + playerId;
    }

    @PostMapping("/logout")
    public String logout(@RequestParam String token) {
        memberService.logout(token);
        return "로그아웃 성공";
    }
}
