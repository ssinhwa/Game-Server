package com.ssinhwa.gameserver.main.controller;

import com.ssinhwa.gameserver.main.dto.UserDto;
import com.ssinhwa.gameserver.main.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserDto userDto) {
        memberService.signUp(userDto);
        return "Sign Up Success";
    }


}
