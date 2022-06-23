package com.eansoft.boardproject.member.service;

import com.eansoft.boardproject.member.domain.Member;

public interface MemberService {

	//회원가입
	int registerMember(Member member);

	//로그인
	Member loginProcess(Member member);

}
