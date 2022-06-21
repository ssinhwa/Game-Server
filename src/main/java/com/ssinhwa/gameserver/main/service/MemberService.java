package com.ssinhwa.gameserver.main.service;

import com.ssinhwa.gameserver.main.dto.LoginDto;
import com.ssinhwa.gameserver.main.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    public void signUp(UserDto userDto);

    public String login(LoginDto loginDto);

    public UserDto findMemberByUsername(String username);

    public void logout(String token);

    public void confirmEmail(String token);
}
