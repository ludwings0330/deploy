package com.example.deploy.members.service;

import com.example.deploy.members.Member;
import com.example.deploy.members.controller.dto.MemberJoinRequestDto;
import com.example.deploy.members.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public void join(MemberJoinRequestDto requestDto) {
        final Member member = Member.builder()
                                   .name(requestDto.getName())
                                   .address(requestDto.getAddress())
                                   .phone(requestDto.getPhone()).build();

        memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
