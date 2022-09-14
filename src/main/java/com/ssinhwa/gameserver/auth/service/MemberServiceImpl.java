package com.ssinhwa.gameserver.auth.service;

import com.ssinhwa.gameserver.auth.dto.LoginDto;
import com.ssinhwa.gameserver.auth.dto.LoginUserDto;
import com.ssinhwa.gameserver.auth.dto.UserDto;
import com.ssinhwa.gameserver.auth.entity.EmailToken;
import com.ssinhwa.gameserver.auth.entity.Member;
import com.ssinhwa.gameserver.auth.jwt.TokenProvider;
import com.ssinhwa.gameserver.auth.repository.MemberRepository;
import com.ssinhwa.gameserver.redisserver.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailTokenService emailTokenService;
    private final TokenProvider tokenProvider;
    private final RedisService redisService;

    @Transactional(readOnly = true)
    public UserDto findMemberByUsername(String username) {
        Member member = memberRepository.findMemberByUsername(username);
        return new UserDto(member.getUsername(), member.getPassword(), member.getEmail());
    }

    @Override
    public void logout(String token) {
        redisService.delToken(token);   // 토큰 삭제
    }

    @Override
    @Transactional
    public void confirmEmail(String token) {
        log.info(token);
        EmailToken emailToken = emailTokenService.findByIdAndExpirationDate(token);
        Member member = memberRepository.findMemberById(emailToken.getMemberId());
        emailToken.useToken();
        member.changeEmailVerified(true);
        memberRepository.save(member);
    }

    @Override
    public void signUp(UserDto userDto) {
        Member findMember = memberRepository.findMemberByUsername(userDto.getUsername());
        if (findMember != null) {
            throw new EntityExistsException("Error : 이미 존재하는 유저입니다.");
        }
        String password = passwordEncoder.encode(userDto.getPassword());
        Member member = new Member(userDto.getUsername(), password, userDto.getEmail());
        memberRepository.save(member);
        emailTokenService.createEmailConfirmationToken(member.getId(), userDto.getEmail());
    }

    @Override
    public LoginUserDto login(LoginDto loginDto) {
        Member member = memberRepository.findMemberByUsername(loginDto.getUsername());
        if (member == null) {
            throw new EntityNotFoundException("Error : 존재하지 않는 유저입니다.");
        }

        if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new EntityNotFoundException("Error : 일치하지 않는 패스워드 입니다.");
        }

        if (!member.isEmailVerified()) {
            throw new EntityNotFoundException("Error : 이메일 인증이 완료되지 않았습니다.");
        }

        String token = tokenProvider.generateToken(member.getUsername());  // 토큰 생성해서 저장
        log.info("Token : " + token);
        redisService.setToken(token, member.getUsername()); // Redis 에 토큰 저장

        return new LoginUserDto(token, member.getId());
    }


}
