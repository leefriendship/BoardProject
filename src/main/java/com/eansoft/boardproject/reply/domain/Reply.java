package com.eansoft.boardproject.reply.domain;

import java.sql.Date;

import com.eansoft.boardproject.member.domain.Member;

public class Reply {
 
	private int replyNo;
	private int boardNo;
	private int parentReplyNo;
	private int replyOrder;
	private int replyDepth;
	private String memberId;
	private String replyContents;
	private Date writeDate;
	private Date updateDate;
	private String replyStatus;
	private Member member;
	
	public Reply() {}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getParentReplyNo() {
		return parentReplyNo;
	}

	public void setParentReplyNo(int parentReplyNo) {
		this.parentReplyNo = parentReplyNo;
	}

	public int getReplyOrder() {
		return replyOrder;
	}

	public void setReplyOrder(int replyOrder) {
		this.replyOrder = replyOrder;
	}

	public int getReplyDepth() {
		return replyDepth;
	}

	public void setReplyDepth(int replyDepth) {
		this.replyDepth = replyDepth;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getReplyContents() {
		return replyContents;
	}

	public void setReplyContents(String replyContents) {
		this.replyContents = replyContents;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", boardNo=" + boardNo + ", parentReplyNo=" + parentReplyNo
				+ ", replyOrder=" + replyOrder + ", replyDepth=" + replyDepth + ", memberId=" + memberId
				+ ", replyContents=" + replyContents + ", writeDate=" + writeDate + ", updateDate=" + updateDate
				+ ", replyStatus=" + replyStatus + ", member=" + member + "]";
	}

	
	
}
