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
        try {
            memberService.login(loginDto);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "로그인 성공";
    }
}
