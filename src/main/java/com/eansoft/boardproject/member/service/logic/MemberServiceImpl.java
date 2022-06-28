package com.eansoft.boardproject.member.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eansoft.boardproject.member.domain.Member;
import com.eansoft.boardproject.member.service.MemberService;
import com.eansoft.boardproject.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberStore mStore;
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입
	@Override
	public int registerMember(Member member) {
		int result = mStore.insertMember(member,sqlSession);
		return result;
	}

	//로그인
	@Override
	public Member loginProcess(Member member) {
		Member memberData = mStore.memberLogin(member,sqlSession);
		return memberData;
	}

	//id 중복체크
	@Override
	public Member idCheck(String memberId) {
		Member member = mStore.checkId(memberId,sqlSession);
		return member;
	}

}
