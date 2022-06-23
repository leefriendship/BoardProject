package com.eansoft.boardproject.board.domain;

import java.sql.Date;
import java.util.List;

import com.eansoft.boardproject.member.domain.Member;

public class Board {

	private int boardNo;
	private String boardType;
	private String boardTitle;
	private String boardContents;
	private String memberId;
	private String writeDate;
	private int boardCount;
	private String boardStatus;
	//게시판 종류명
	private String boardTypeName;
	//첨부파일 개수
	private String boardFileCount;
	//게시글 작성자 정보
	private Member member;
	//게시글 첨부파일
	private List<BoardFile> fList;
	
	
	public Board() {}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	public String getBoardType() {
		return boardType;
	}


	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}


	public String getBoardTitle() {
		return boardTitle;
	}


	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}


	public String getBoardContents() {
		return boardContents;
	}


	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getWriteDate() {
		return writeDate;
	}


	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}


	public int getBoardCount() {
		return boardCount;
	}


	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}


	public String getBoardStatus() {
		return boardStatus;
	}


	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
	}


	public String getBoardTypeName() {
		return boardTypeName;
	}


	public void setBoardTypeName(String boardTypeName) {
		this.boardTypeName = boardTypeName;
	}


	public String getBoardFileCount() {
		return boardFileCount;
	}


	public void setBoardFileCount(String boardFileCount) {
		this.boardFileCount = boardFileCount;
	}


	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}


	public List<BoardFile> getfList() {
		return fList;
	}


	public void setfList(List<BoardFile> fList) {
		this.fList = fList;
	}


	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardType=" + boardType + ", boardTitle=" + boardTitle
				+ ", boardContents=" + boardContents + ", memberId=" + memberId + ", writeDate=" + writeDate
				+ ", boardCount=" + boardCount + ", boardStatus=" + boardStatus + ", boardTypeName=" + boardTypeName
				+ ", boardFileCount=" + boardFileCount + ", member=" + member + ", fList=" + fList + "]";
	}

}
