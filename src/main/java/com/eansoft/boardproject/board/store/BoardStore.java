package com.eansoft.boardproject.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.eansoft.boardproject.board.domain.Board;
import com.eansoft.boardproject.board.domain.BoardFile;
import com.eansoft.boardproject.board.domain.PageInfo;
import com.eansoft.boardproject.board.domain.Search;

@Repository
public interface BoardStore {

	//게시글 등록
	int insertBoard(SqlSession sqlSession, Board board);

	//첨부파일 등록
	int insertBoardFile(SqlSession sqlSession, BoardFile boardFile);

	//게시글 수정
	int updateBoard(SqlSession sqlSession, Board board);
	
	
	//게시판 목록 조회
	List<Board> selectBoardList(SqlSession sqlSession,PageInfo pi);

	List<Board> selectBoardList(SqlSession sqlSession);
	
	//게시글 상세 조회
	Board selectOneBoard(SqlSession sqlSession, int boardNo);

	//조회수 증가
	int updateViewCount(SqlSession sqlSession, int boardNo);

	//게시글 개수 구하기
	int selectBoardListCount(SqlSession sqlSession);

	//검색 조회
	List<Board> boardSearchList(SqlSession sqlSession, Search search,PageInfo pi);
	List<Board> boardSearchList(SqlSession sqlSession, Search search);

	//검색된 게시물 개수 구하기
	int selectBoardSearchListCount(SqlSession sqlSession, Search search);

	//게시글 파일 조회
	List<BoardFile> selectBoardFiles(SqlSession sqlSession, int boardNo);

	//게시글 파일 삭제
	int deleteBoardFile(SqlSession sqlSession, int fileNo);

	//게시글 삭제(비활성화)
	int deleteBoard(SqlSession sqlSession, int boardNo);

	



}
