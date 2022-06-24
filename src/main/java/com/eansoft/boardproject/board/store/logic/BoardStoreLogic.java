package com.eansoft.boardproject.board.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.eansoft.boardproject.board.domain.Board;
import com.eansoft.boardproject.board.domain.BoardFile;
import com.eansoft.boardproject.board.domain.PageInfo;
import com.eansoft.boardproject.board.domain.Search;
import com.eansoft.boardproject.board.store.BoardStore;

@Repository
public class BoardStoreLogic implements BoardStore{

	//게시글 등록
	@Override
	public int insertBoard(SqlSession sqlSession, Board board) {
		int result = sqlSession.insert("BoardMapper.insertBoard",board);
		return result;
	}

	//첨부파일 등록
	@Override
	public int insertBoardFile(SqlSession sqlSession, BoardFile boardFile) {
		int result = sqlSession.insert("BoardMapper.insertBoardFile",boardFile);
		return result;
	}

	//게시글 수정
	@Override
	public int updateBoard(SqlSession sqlSession, Board board) {
		int result = sqlSession.update("BoardMapper.updateBoard",board);
		return result;
	}
	
	//게시판 목록 조회
	@Override
	public List<Board> selectBoardList(SqlSession sqlSession,PageInfo pi) {
		int limit = pi.getListLimit();
		int currentPage = pi.getCurrentPage();
		int offset = (currentPage - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Board> bList = sqlSession.selectList("BoardMapper.selectBoardList",pi,rowBounds);
		return bList;
	}

	@Override
	public List<Board> selectBoardList(SqlSession sqlSession) {
		List<Board> bList = sqlSession.selectList("BoardMapper.selectBoardList");
		return bList;
	}
	
	//게시글 상세 조회
	@Override
	public Board selectOneBoard(SqlSession sqlSession, int boardNo) {
		Board board = sqlSession.selectOne("BoardMapper.selectOneBoard", boardNo);
		return board;
	}

	//조회수 증가
	@Override
	public int updateViewCount(SqlSession sqlSession, int boardNo) {
		int viewCount = sqlSession.update("BoardMapper.updateViewCount",boardNo);
		return viewCount;
	}

	//게시글 개수 구하기
	@Override
	public int selectBoardListCount(SqlSession sqlSession) {
		int listCount = sqlSession.selectOne("BoardMapper.selectBoardListCount");
		return listCount;
	}

	//검색조회
	@Override
	public List<Board> boardSearchList(SqlSession sqlSession, Search search,PageInfo pi) {
		int limit = pi.getListLimit();
		int currentPage = pi.getCurrentPage();
		int offset = (currentPage - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Board> searchList = sqlSession.selectList("BoardMapper.boardSearchList",search,rowBounds);
		return searchList;
	}
	@Override
	public List<Board> boardSearchList(SqlSession sqlSession, Search search) {
		List<Board> searchList = sqlSession.selectList("BoardMapper.boardSearchList",search);
		return searchList;
	}
		
	
	//검색 게시물 개수 구하기
	@Override
	public int selectBoardSearchListCount(SqlSession sqlSession, Search search) {
		int listCount = sqlSession.selectOne("BoardMapper.selectBoardSearchListCount",search);
		return listCount;
	}

	//게시글 파일 조회
	@Override
	public List<BoardFile> selectBoardFiles(SqlSession sqlSession, int boardNo) {
		List<BoardFile> fList = sqlSession.selectList("BoardMapper.selectBoardFiles", boardNo);
		return fList;
	}

	//게시글 파일 삭제
	@Override
	public int deleteBoardFile(SqlSession sqlSession, int fileNo) {
		int result = sqlSession.delete("BoardMapper.deleteBoardFile", fileNo);
		return result;
	}

	//게시글 삭제(비활성화)
	@Override
	public int deleteBoard(SqlSession sqlSession, int boardNo) {
		int result = sqlSession.update("BoardMapper.deleteBoard",boardNo);
		return result;
	}

	



	

}
