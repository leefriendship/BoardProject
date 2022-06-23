package com.eansoft.boardproject.reply.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.eansoft.boardproject.reply.domain.Reply;
import com.eansoft.boardproject.reply.store.ReplyStore;

@Repository
public class ReplyStoreLogic implements ReplyStore{
	//댓글 조회
	@Override
	public List<Reply> selectAllReply(SqlSession sqlSession, Reply reply) {
		List<Reply> nReply = sqlSession.selectList("ReplyMapper.selectAllReply", reply);
		return nReply;
	}
	//댓글 등록
	@Override
	public int insertReply(SqlSession sqlSession, Reply reply) {
		int result = sqlSession.insert("ReplyMapper.insertReply",reply);
		return result;
	}

	//댓글 수정
	@Override
	public int updateReply(SqlSession sqlSession, Reply reply) {
		int result = sqlSession.update("ReplyMapper.updateReply",reply);
		return result;
	}
	//댓글 삭제
	@Override
	public int deleteReply(SqlSession sqlSession, int replyNo) {
		int result = sqlSession.update("ReplyMapper.deleteReply",replyNo);
		return result;
	}

	//답댓글
	@Override
	public int insertReReply(SqlSession sqlSession, Reply reply) {
		int result = sqlSession.insert("ReplyMapper.insertReReply",reply);
		return result;
	}
}
