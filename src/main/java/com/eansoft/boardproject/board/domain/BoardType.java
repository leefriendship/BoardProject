package com.eansoft.boardproject.board.domain;

public class BoardType {

	private int boardTypeNo;
	private int boardTypeName;
	
	public BoardType() {}

	public int getBoardTypeNo() {
		return boardTypeNo;
	}

	public void setBoardTypeNo(int boardTypeNo) {
		this.boardTypeNo = boardTypeNo;
	}

	public int getBoardTypeName() {
		return boardTypeName;
	}

	public void setBoardTypeName(int boardTypeName) {
		this.boardTypeName = boardTypeName;
	}

	@Override
	public String toString() {
		return "BoardType [boardTypeNo=" + boardTypeNo + ", boardTypeName=" + boardTypeName + "]";
	}
	
	
}
