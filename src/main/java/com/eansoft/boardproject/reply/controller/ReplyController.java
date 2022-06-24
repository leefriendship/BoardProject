package com.eansoft.boardproject.reply.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eansoft.boardproject.reply.domain.Reply;
import com.eansoft.boardproject.reply.service.ReplyService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

@Controller
public class ReplyController {

	@Autowired
	public ReplyService rService;
	
	
	// 공지글의 댓글 조회
		@ResponseBody
		@RequestMapping(value = "/reply/list.eansoft", method = RequestMethod.GET)
		public void boardReplyView(@ModelAttribute Reply reply, HttpServletResponse response)
				throws JsonIOException, IOException {

			List<Reply> nReplyList = rService.printAllReply(reply);
			if (!nReplyList.isEmpty()) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				gson.toJson(nReplyList, response.getWriter());

			}

		}

		// 공지글의 댓글 등록
		@ResponseBody
		@RequestMapping(value = "/reply/add.eansoft", method = RequestMethod.POST)

		public String registerReply(@ModelAttribute Reply reply, HttpServletRequest request) {
			HttpSession session = request.getSession();
			String memberId = (String) session.getAttribute("memberId");
			reply.setMemberId(memberId);

			int result = rService.registerReply(reply);
			if (result > 0) {
				return "success";
			} else {
				return "fail";
			}
		}

		// 공지글의 댓글 수정
		@ResponseBody
		@RequestMapping(value = "/reply/modify.eansoft", method = RequestMethod.POST)
		public String modifyReply(@ModelAttribute Reply reply) {
			int result = rService.modifyReply(reply);
			if (result > 0) {
				return "success";
			} else {
				return "fail";
			}
		}

		// 대댓글 작성
		@ResponseBody
		@RequestMapping(value = "/register/reReply.eansoft", method = RequestMethod.POST)

		public String addReReply(@ModelAttribute Reply reply, HttpServletRequest request) {
			HttpSession session = request.getSession();
			String memberId = (String) session.getAttribute("memberId");
			reply.setMemberId(memberId);

			int result = rService.addReReply(reply);
			if (result > 0) {
				return "success";
			} else {
				return "fail";
			}
		}

		// 공지글의 댓글 삭제
		@ResponseBody
		@RequestMapping(value = "/reply/delete.eansoft", method = RequestMethod.GET)
		public String removeReply(@RequestParam("replyNo") int replyNo) {
			int result = rService.removeReply(replyNo);
			if (result > 0) {
				return "success";
			} else {
				return "fail";
			}
		}
	
}
