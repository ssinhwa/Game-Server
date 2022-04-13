package com.ssinhwa.gameserver.main.controller;

import com.ssinhwa.gameserver.main.dto.LoginDto;
import com.ssinhwa.gameserver.main.dto.UserDto;
import com.ssinhwa.gameserver.main.service.MemberServiceImpl;
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
        memberService.signUp(userDto);
        return "Sign Up Success";
    }

    @GetMapping("/login/success")
    public String success() {
        return "login Success";
    }

    @GetMapping("confirm-email")
    public String confirmEmail(@RequestParam String token) {
        memberService.confirmEmail(token);
        return "이메일 인증 완료!";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        memberService.login(loginDto);
        return "로그인 성공";
    }

    @GetMapping("/login/fail")
    public String fail() {
        return "login fail";
    }
}
