package com.eansoft.boardproject.member.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.eansoft.boardproject.common.SaveAttachedFile;
import com.eansoft.boardproject.member.domain.Member;
import com.eansoft.boardproject.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;

	//회원가입 페이지
	@RequestMapping(value="/member/joinView.eansoft", method=RequestMethod.GET)
	public ModelAndView joinView(ModelAndView mv) {
		mv.setViewName("member/joinPage");
		return mv;
	}
	
	//로그인 페이지
	@RequestMapping(value="/loginView.eansoft", method=RequestMethod.GET)
	public ModelAndView loginView(ModelAndView mv) {
		mv.setViewName("redirect:/index.jsp");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/join/idCheck.eansoft", method = RequestMethod.POST)
	public String removeReply(@RequestParam("memberId") String memberId) {
		Member member = mService.idCheck(memberId);
		if (member!=null) {
			return "success";
		} else {
			return "fail";
		}
	} 
	
	
	//회원가입
	@RequestMapping(value="/register/member.eansoft", method=RequestMethod.POST)
	public ModelAndView registerMember(ModelAndView mv, @ModelAttribute Member member
			,@RequestParam(value="profileImg", required=false) MultipartFile uploadFile
			,HttpServletRequest request) {
		try {
			if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
			       HashMap<String, String> fileMap = SaveAttachedFile.saveFile(uploadFile, request); // 업로드한 파일 저장하고 경로 리턴
			       String filePath = fileMap.get("filePath");
			       String fileRename = fileMap.get("fileRename");
			       if(filePath != null && !filePath.equals("")) {
			          member.setMemberPhoto(fileRename); // 추가
			       }
			    }
			int result = mService.registerMember(member);
			if(result > 0) {
				mv.setViewName("redirect:/index.jsp");
			}else {
				mv.setViewName("common/errorPage");
				mv.addObject("msg","회원가입 실패");
			}
		}catch(Exception e){
			mv.setViewName("common/errorPage");
			mv.addObject("msg",e.toString());
		}
		return mv;
	}
	
	//로그인
	@RequestMapping(value="/login.eansoft", method=RequestMethod.POST)
	public ModelAndView loginProcess(ModelAndView mv, HttpServletRequest request,@ModelAttribute Member member) {
		try {
			Member memberData = mService.loginProcess(member);
			if(memberData != null) {
				HttpSession session = request.getSession();
				session.setAttribute("memberId", memberData.getMemberId());
				session.setAttribute("memberName", memberData.getMemberName());
				session.setAttribute("memberPhoto", memberData.getMemberPhoto());
				mv.setViewName("redirect:/index.jsp");
			}else {
				mv.setViewName("common/alert");
				 mv.addObject("msg", "아이디 또는 비밀번호를 잘못 입력했습니다.");
		         mv.addObject("url","index.jsp");
			}
		}catch(Exception e) {
			mv.setViewName("common/errorPage");
			mv.addObject("msg",e.toString());
		}
		return mv;
	}
	
	//로그아웃
	@RequestMapping(value="/logout.eansoft",method=RequestMethod.GET)
	public ModelAndView logout(ModelAndView mv, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
			mv.setViewName("redirect:/index.jsp");
		} else {
			mv.addObject("msg", "로그아웃에 실패했습니다.");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	
}
