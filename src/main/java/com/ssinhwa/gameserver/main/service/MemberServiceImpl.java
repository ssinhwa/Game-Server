package com.ssinhwa.gameserver.main.service;

import com.ssinhwa.gameserver.main.dto.UserDto;
import com.ssinhwa.gameserver.main.entity.Member;
import com.ssinhwa.gameserver.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void signUp(UserDto userDto) {
        String password = passwordEncoder.encode(userDto.getPassword());
        Member member = new Member(userDto.getUsername(), password, userDto.getEmail());
        userRepository.save(member);
    }

    @Override
    public void login(String username, String password) throws Exception {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetailsServiceImpl userDetail = (UserDetailsServiceImpl) authenticate.getPrincipal();

    }
}
