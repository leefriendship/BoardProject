package com.eansoft.boardproject.board.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eansoft.boardproject.board.domain.Board;
import com.eansoft.boardproject.board.domain.BoardFile;
import com.eansoft.boardproject.board.domain.PageInfo;
import com.eansoft.boardproject.board.domain.Search;
import com.eansoft.boardproject.board.service.BoardService;
import com.eansoft.boardproject.board.store.BoardStore;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardStore bStore;
	
	@Autowired
	private SqlSession sqlSession;

	//게시글 등록
	@Override
	public int registerBoard(Board board) {
		int result = bStore.insertBoard(sqlSession,board);
		return result;
	}

	//첨부파일 등록
	@Override
	public int registerBoardFile(BoardFile boardFile) {
		int result = bStore.insertBoardFile(sqlSession, boardFile);
		return result;
	}

	//게시판 목록 조회
	@Override
	public List<Board> allBoardList(PageInfo pi) {
		List<Board> bList = bStore.selectBoardList(sqlSession,pi);
		return bList;
	}
	
	@Override
	public List<Board> allBoardList() {
		List<Board> bList = bStore.selectBoardList(sqlSession);
		return bList;
	}

	//게시글 상세 조회
	@Override
	public Board boardDetail(int boardNo) {
		Board board = bStore.selectOneBoard(sqlSession,boardNo);
		return board;
	}

	//조회수 증가
	@Override
	public int viewCount(int boardNo) {
		int viewCount = bStore.updateViewCount(sqlSession,boardNo);
		return viewCount;
	}
	
	//게시글 개수 구하기
	@Override
	public int boardListCount() {
		int listCount = bStore.selectBoardListCount(sqlSession);
		return listCount;
	}

	//검색 조회
	@Override
	public List<Board> boardSearchList(Search search,PageInfo pi) {
		List<Board> searchList = bStore.boardSearchList(sqlSession,search,pi);
		return searchList;
	}

	//검색 게시물 개수 구하기
	@Override
	public int boardSearchListCount(Search search) {
		int listCount = bStore.selectBoardSearchListCount(sqlSession,search);
		return listCount;
	}

	//게시글 파일 조회
	@Override
	public List<BoardFile> boardDetailFiles(int boardNo) {
		List<BoardFile>  fList = bStore.selectBoardFiles(sqlSession,boardNo);
		return fList;
	}

	//파일 삭제
	@Override
	public int removeBoardFile(int fileNo) {
		int result = bStore.deleteBoardFile(sqlSession,fileNo);
		return result;
	}

	//게시글 수정
	@Override
	public int modifyBoard(Board board) {
		int result = bStore.updateBoard(sqlSession,board);
		return result;
	}

	//게시글 삭제(비활성화)
	@Override
	public int removeBoard(int boardNo) {
		int result = bStore.deleteBoard(sqlSession, boardNo);
		return result;
	}
	
	
	
}
