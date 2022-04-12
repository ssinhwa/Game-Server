package com.ssinhwa.gameserver.main.service;

import com.ssinhwa.gameserver.main.dto.UserDto;
import com.ssinhwa.gameserver.main.entity.Member;
import com.ssinhwa.gameserver.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserDto findMemberByUsername(String username) {
        Member member = memberRepository.findMemberByUsername(username);
        return new UserDto(member.getUsername(), member.getPassword(), member.getEmail());
    }

    @Override
    public void signUp(UserDto userDto) {
        String password = passwordEncoder.encode(userDto.getPassword());
        Member member = new Member(userDto.getUsername(), password, userDto.getEmail());
        memberRepository.save(member);
    }

}
