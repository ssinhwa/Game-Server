package com.ssinhwa.gameserver.main.service;

import com.ssinhwa.gameserver.main.dto.LoginDto;
import com.ssinhwa.gameserver.main.dto.UserDto;
import com.ssinhwa.gameserver.main.entity.EmailToken;
import com.ssinhwa.gameserver.main.entity.Member;
import com.ssinhwa.gameserver.main.jwt.TokenProvider;
import com.ssinhwa.gameserver.main.repository.MemberRepository;
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

    @Transactional(readOnly = true)
    public UserDto findMemberByUsername(String username) {
        Member member = memberRepository.findMemberByUsername(username);
        return new UserDto(member.getUsername(), member.getPassword(), member.getEmail());
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
            throw new EntityExistsException("이미 존재하는 유저입니다.");
        }
        String password = passwordEncoder.encode(userDto.getPassword());
        Member member = new Member(userDto.getUsername(), password, userDto.getEmail());
        memberRepository.save(member);

        emailTokenService.createEmailConfirmationToken(member.getId(), userDto.getEmail());
    }

    @Override
    public void login(LoginDto loginDto) {
        Member member = memberRepository.findMemberByUsername(loginDto.getUsername());
        if (member == null) {
            throw new EntityNotFoundException("존재하지 않는 유저입니다.");
        }

        if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new EntityNotFoundException("일치하지 않는 패스워드 입니다.");
        }

        if (!member.isEmailVerified()) {
            throw new EntityNotFoundException("이메일 인증이 완료되지 않았습니다.");
        }

        String token = tokenProvider.generateToken(member.getUsername());  // 토큰 생성해서 저장
        log.info("Token : " + token);
        member.setToken(token);
        memberRepository.save(member);
    }

}
