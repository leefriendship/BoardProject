package com.eansoft.boardproject.member.store;

import org.apache.ibatis.session.SqlSession;

import com.eansoft.boardproject.member.domain.Member;

public interface MemberStore {

	//회원가입
	int insertMember(Member member, SqlSession sqlSession);

	//로그인
	Member memberLogin(Member member, SqlSession sqlSession);

}
