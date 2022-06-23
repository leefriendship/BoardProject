package com.eansoft.boardproject.board.service;

import java.util.List;

import com.eansoft.boardproject.board.domain.Board;
import com.eansoft.boardproject.board.domain.BoardFile;
import com.eansoft.boardproject.board.domain.PageInfo;
import com.eansoft.boardproject.board.domain.Search;

public interface BoardService {

	//게시글 등록
	int registerBoard(Board board);

	//첨부파일 등록
	int registerBoardFile(BoardFile boardFile);

	//게시글 수정
	int modifyBoard(Board board);
	
	
	//게시판 목록 조회
	List<Board> allBoardList(PageInfo pi);
	List<Board> allBoardList();
	//게시글 상세 조회
	Board boardDetail(int boardNo);

	//조회수 증가
	int viewCount(int boardNo);

	//게시글 개수 구하기
	int boardListCount();

	//검색 조회
	List<Board> boardSearchList(Search search,PageInfo pi);

	//검색 게시물 개수 구하기
	int boardSearchListCount(Search search);

	//게시글 파일 조회
	List<BoardFile> boardDetailFiles(int boardNo);

	//파일 삭제
	int removeBoardFile(int fileNo);

	//게시글 삭제(비활성화)ㄹ
	int removeBoard(int boardNo);
	
	

}
