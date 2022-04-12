package com.ssinhwa.gameserver.main.service;

import com.ssinhwa.gameserver.main.dto.UserDetailVO;
import com.ssinhwa.gameserver.main.entity.Member;
import com.ssinhwa.gameserver.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetailVO loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Member member = memberRepository.findMemberByUsername(username);
            Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
            if (member.getUsername().equals("admin")) {
                grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new UserDetailVO(member.getUsername(), member.getPassword(), grantedAuthoritySet);
        } catch (Exception e) {
            log.info("User 아이디 찾지 못함");
            throw new UsernameNotFoundException(username);
        }
    }
}
