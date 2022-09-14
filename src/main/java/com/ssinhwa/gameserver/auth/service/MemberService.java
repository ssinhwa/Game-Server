package com.ssinhwa.gameserver.auth.service;

import com.ssinhwa.gameserver.auth.dto.LoginDto;
import com.ssinhwa.gameserver.auth.dto.LoginUserDto;
import com.ssinhwa.gameserver.auth.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    public void signUp(UserDto userDto);

    public LoginUserDto login(LoginDto loginDto);

    public UserDto findMemberByUsername(String username);

    public void logout(String token);

    public void confirmEmail(String token);
}
