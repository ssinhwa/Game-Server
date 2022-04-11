package com.ssinhwa.gameserver.main.service;

import com.ssinhwa.gameserver.main.dto.UserDto;
import com.ssinhwa.gameserver.main.entity.CustomUserDetails;
import com.ssinhwa.gameserver.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userRepository.findCustomUserDetailsByUsername(username);
        } catch (Exception e) {
            log.info("User 아이디 찾지 못함");
            throw new UsernameNotFoundException(username);
        }
    }

    public Long signUp(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(userDto.getPassword());
        CustomUserDetails newUser = new CustomUserDetails(userDto.getUsername(), password, userDto.getEmail(), userDto.getAuth());
        newUser.changeLocked(true);
        newUser.changeEmailVerified(false);
        System.out.println("newUser = " + newUser);
        userRepository.save(newUser);
        return newUser.getId();
    }
}
