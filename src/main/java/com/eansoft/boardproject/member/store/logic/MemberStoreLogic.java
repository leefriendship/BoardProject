package com.eansoft.boardproject.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.eansoft.boardproject.member.domain.Member;
import com.eansoft.boardproject.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{

	//회원가입
	@Override
	public int insertMember(Member member, SqlSession sqlSession) {
		int result = sqlSession.insert("MemberMapper.insertMember",member);
		return result;
	}

	//로그인
	@Override
	public Member memberLogin(Member member, SqlSession sqlSession) {
		Member MemberData = sqlSession.selectOne("MemberMapper.memberLogin", member);
		return MemberData;
	}

	//id check
	@Override
	public Member checkId(String memberId, SqlSession sqlSession) {
		Member member = sqlSession.selectOne("MemberMapper.checkId", memberId);
		return member;
	}



}
