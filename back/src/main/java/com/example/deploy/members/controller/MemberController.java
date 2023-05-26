package com.example.deploy.members.controller;

import com.example.deploy.members.Member;
import com.example.deploy.members.controller.dto.MemberJoinRequestDto;
import com.example.deploy.members.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/members")
    public List<Member> getAllMembers() {
        log.info("모든 멤버 조회");
        return memberService.findAllMembers();
    }

    @PostMapping("/api/members")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void join(@RequestBody MemberJoinRequestDto requestDto) {
        log.info("회원 가입");
        memberService.join(requestDto);
    }

    @DeleteMapping("/api/members/{id}")
    public void delete(@PathVariable Long id) {
        log.info("회원 삭제");
        memberService.deleteMember(id);
    }
}
