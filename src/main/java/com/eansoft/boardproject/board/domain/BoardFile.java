package com.eansoft.boardproject.board.domain;

public class BoardFile {

	private int fileNo;
	private int boardNo;
	private String fileName;
	private String fileRename;
	private String filePath;
	
	public BoardFile() {}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRename() {
		return fileRename;
	}

	public void setFileRename(String fileRename) {
		this.fileRename = fileRename;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "BoardFile [fileNo=" + fileNo + ", boardNo=" + boardNo + ", fileName=" + fileName + ", fileRename="
				+ fileRename + ", filePath=" + filePath + "]";
	}
	
	
}
