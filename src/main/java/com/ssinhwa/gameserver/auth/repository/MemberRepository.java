package com.ssinhwa.gameserver.auth.repository;

import com.ssinhwa.gameserver.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    public Member findMemberByUsername(String username);

    public Member findMemberById(Long id);
}
