package com.ssinhwa.gameserver.main.service;

import com.ssinhwa.gameserver.main.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    public void signUp(UserDto userDto);

    public void login(String username, String password) throws Exception;

}
