package com.ssinhwa.gameserver.main.controller;

import com.ssinhwa.gameserver.main.dto.UserDto;
import com.ssinhwa.gameserver.main.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserDto userDto) {
        userService.signUp(userDto);
        return "Sign Up Success";
    }
}
