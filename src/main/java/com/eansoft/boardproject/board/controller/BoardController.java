package com.eansoft.boardproject.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.eansoft.boardproject.common.Pagination;
import com.eansoft.boardproject.board.domain.Board;
import com.eansoft.boardproject.board.domain.BoardFile;
import com.eansoft.boardproject.board.domain.PageInfo;
import com.eansoft.boardproject.board.domain.Search;
import com.eansoft.boardproject.board.service.BoardService;
import com.eansoft.boardproject.common.SaveAttachedFile;

@Controller
public class BoardController {

	@Autowired
	private BoardService bService;

	// 게시글 등록 페이지
	@RequestMapping(value = "/write/boardPage.eansoft", method = RequestMethod.GET)
	public ModelAndView writeBoardPage(ModelAndView mv) {
		mv.setViewName("/board/writeBoardPage");
		return mv;
	}

	//통계 페이지
		@RequestMapping(value = "/board/statisticPage.eansoft", method = RequestMethod.GET)
		public ModelAndView statisticPage(ModelAndView mv) {
			mv.setViewName("/api/statistic");
			return mv;
		}
		
	//IP 접속정보 페이지	
		@RequestMapping(value ="/remoteAddr/infoPage.eansoft", method = RequestMethod.GET)
		public ModelAndView remoteAddrInfoPage(ModelAndView mv) {
			mv.setViewName("/api/remoteApprInfo");
			return mv;
		}
	
	// 게시글 수정 페이지
	@RequestMapping(value = "/board/modifyPage.eansoft", method = RequestMethod.GET)
	public ModelAndView modifyBoardPage(ModelAndView mv, @RequestParam("boardNo") int boardNo) {
		try {
			Board board = bService.boardDetail(boardNo);
			if (board != null) {
				mv.addObject("board", board);
				mv.setViewName("/board/modifyBoardPage");
			} else {
				mv.addObject("msg", "게시글 조회 실패");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// 게시글 등록
	@RequestMapping(value = "/register/board.eansoft")
	public ModelAndView registerBoard(ModelAndView mv, @ModelAttribute Board board,
			@RequestParam(value = "uploadFiles", required = false) List<MultipartFile> multipartfile,
			HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			String memberId = (String) session.getAttribute("memberId");
			board.setMemberId(memberId);
			// 게시판 테이블 등록
			int result = bService.registerBoard(board);
			if (result <= 0) {
				mv.addObject("msg", "게시글 등록 실패");
				mv.setViewName("common/errorPage");
			}

			// 첨부파일이 있을 경우 진행
			if (multipartfile.size() > 0 && !multipartfile.get(0).getOriginalFilename().equals("")) {
				for (int i = 0; i < multipartfile.size(); i++) {
					HashMap<String, String> fileMap = SaveAttachedFile.saveFile(multipartfile.get(i), request);
					String fileName = fileMap.get("fileName");
					String fileRename = fileMap.get("fileRename");
					String filePath = fileMap.get("filePath");
					// 첨부파일 테이블 등록
					BoardFile boardFile = new BoardFile();
					boardFile.setFileName(fileName);
					boardFile.setFileRename(fileRename);
					boardFile.setFilePath(filePath);

					int fResult = bService.registerBoardFile(boardFile);
					if (fResult <= 0) {
						mv.addObject("msg", "게시글 첨부파일 등록 실패");
						mv.setViewName("common/errorPage");
					}
				}
			}
			mv.setViewName("redirect:/board/list.eansoft");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// 게시물 수정
	@RequestMapping(value = "/modify/board.eansoft", method = RequestMethod.POST)
	public ModelAndView midifyBoard(ModelAndView mv, @ModelAttribute Board board,
			@RequestParam(value = "reloadFiles", required = false) List<MultipartFile> multipartfile,
			HttpServletRequest request) {
		try {
			//첨부파일이 있을경우
			//기존파일 삭제하고 새파일 업로드,디비에 저장
			if (multipartfile.size() > 0 && !multipartfile.get(0).getOriginalFilename().equals("")) {
				int boardNo = board.getBoardNo();
				List<BoardFile> fList = bService.boardDetailFiles(board.getBoardNo());
				// 기존 파일 삭제(기존 파일 이름 필요)
				for (int i = 0; i < fList.size(); i++) {
					int fileNo = fList.get(i).getFileNo();
					int deleteResult = bService.removeBoardFile(fileNo);
					if (deleteResult <= 0) {
						mv.addObject("msg", "게시글 첨부파일 삭제 실패");
						mv.setViewName("common/errorPage");
					}
					String filePath = fList.get(i).getFilePath();
					SaveAttachedFile.deleteFile(filePath, request);
				}
				for (int i = 0; i < multipartfile.size(); i++) {
					HashMap<String, String> fileMap = SaveAttachedFile.saveFile(multipartfile.get(i), request);
					String fileName = fileMap.get("fileName");
					String fileRename = fileMap.get("fileRename");
					String filePath = fileMap.get("filePath");
					// 첨부파일 테이블 등록
					BoardFile boardFile = new BoardFile();
					boardFile.setBoardNo(boardNo);
					boardFile.setFileName(fileName);
					boardFile.setFileRename(fileRename);
					boardFile.setFilePath(filePath);

					int fResult = bService.registerBoardFile(boardFile);
					if (fResult <= 0) {
						mv.addObject("msg", "게시글 첨부파일 등록 실패");
						mv.setViewName("common/errorPage");
					}
				}
			}
			// 디비에 해당 데이터 저장
			int result = bService.modifyBoard(board);
			if (result > 0) {
				mv.setViewName("redirect:/board/list.eansoft");
			} else {
				// 실패
				mv.addObject("msg", "공지사항 수정 실패");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	//게시글 삭제
	@RequestMapping(value="/remove/board.eansoft", method=RequestMethod.GET)
	public ModelAndView removeBoard(ModelAndView mv,@RequestParam("boardNo")int boardNo) {
		try {
		int result = bService.removeBoard(boardNo);
			if(result > 0) {
				mv.setViewName("redirect:/board/list.eansoft");
			}else {
				mv.addObject("msg", "게시글 삭제 실패");
				mv.setViewName("/common/errorPage");
			}
		}catch(Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("/common/errorPage");
		}
		return mv;
	}
	
	
	// 게시판 목록 조회
	@RequestMapping(value = "/board/list.eansoft", method = RequestMethod.GET)
	public ModelAndView allBoardList(ModelAndView mv, @RequestParam(value = "page", required = false) Integer page) {
		try {
			int currentPage = (page != null) ? page : 1;
			int totalCount = bService.boardListCount();
			PageInfo pi = Pagination.getPageInfo(currentPage, totalCount);
			List<Board> bList = bService.allBoardList(pi);
			if (!bList.isEmpty()) {
				mv.addObject("bList", bList);
				mv.addObject("pi", pi);
				mv.addObject("listType","basicList");
				mv.setViewName("/board/boardList");
			} else {
				mv.addObject("msg", "게시글이 없습니다.");
				mv.addObject("listType","basicList");
				mv.setViewName("/board/boardList");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("/common/errorPage");
		}
		return mv;
	}

	// 게시글 상세 조회
	@RequestMapping(value = "/board/detail.eansoft", method = RequestMethod.GET)
	public ModelAndView boardDetail(ModelAndView mv, @RequestParam("boardNo") int boardNo) {
		int boardViewCount = bService.viewCount(boardNo);
		Board board = bService.boardDetail(boardNo);
		try {
			if (board != null) {
				mv.addObject("board", board);
				mv.setViewName("/board/boardDetail");
			} else {
				mv.addObject("msg", "게시글 조회 실패");
				mv.setViewName("/common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("/common/errorPage");
		}
		return mv;
	}

	// 게시판 검색 조회
	@RequestMapping(value = "/board/searchList.eansoft", method = RequestMethod.GET)
	public ModelAndView boardSearchList(ModelAndView mv, @ModelAttribute Search search,
			@RequestParam(value = "page", required = false) Integer page) {
		try {
			int currentPage = (page != null) ? page : 1;
			int totalCount = bService.boardSearchListCount(search);
			PageInfo pi = Pagination.getPageInfo(currentPage, totalCount);
			List<Board> searchList = bService.boardSearchList(search,pi);
			if (!searchList.isEmpty()) {
				mv.addObject("bList", searchList);
				mv.addObject("pi", pi);
				mv.addObject("listType","searchList");
				mv.setViewName("/board/boardList");
			} else {
				mv.addObject("msg", "검색된 결과가 없습니다.");
				mv.addObject("listType","searchList");
				mv.setViewName("/board/boardList");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("/common/errorPage");
		}

		return mv;
	}

	//엑셀 파일 다운로드
	@RequestMapping(value = "/download/boardList.eansoft", method = RequestMethod.GET)
	public void downloadExcel(HttpServletResponse response) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("게시판 전체 목록");
		int rowNo = 0;
		
		Row headerRow = sheet.createRow(rowNo++);
		headerRow.createCell(0).setCellValue("번호");
		headerRow.createCell(1).setCellValue("종류");
		headerRow.createCell(2).setCellValue("작성자");
		headerRow.createCell(3).setCellValue("제목");
		headerRow.createCell(4).setCellValue("내용");
		headerRow.createCell(5).setCellValue("작성일");
		headerRow.createCell(6).setCellValue("첨부파일 개수");
		
		List<Board> bList = bService.allBoardList();
		for(Board board : bList) {
			Row row = sheet.createRow(rowNo++);
			row.createCell(0).setCellValue(board.getBoardNo());
			row.createCell(1).setCellValue(board.getBoardTypeName());
			row.createCell(2).setCellValue(board.getMemberId());
			row.createCell(3).setCellValue(board.getBoardTitle());
			row.createCell(4).setCellValue(board.getBoardContents());
			row.createCell(5).setCellValue(board.getWriteDate());
			row.createCell(6).setCellValue(board.getBoardFileCount());
		}
		
		response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=boardlist.xls");
 
        workbook.write(response.getOutputStream());
        workbook.close();
		
	}
	
	//엑셀 파일 다운로드
		@RequestMapping(value = "/download/boardSearchList.eansoft", method = RequestMethod.GET)
		public void downloadSearchExcel(HttpServletResponse response,@ModelAttribute Search search,
				@RequestParam(value = "page", required = false) Integer page) throws IOException {
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet("게시판 전체 목록");
			int rowNo = 0;
			
			Row headerRow = sheet.createRow(rowNo++);
			headerRow.createCell(0).setCellValue("번호");
			headerRow.createCell(1).setCellValue("종류");
			headerRow.createCell(2).setCellValue("작성자");
			headerRow.createCell(3).setCellValue("제목");
			headerRow.createCell(4).setCellValue("내용");
			headerRow.createCell(5).setCellValue("작성일");
			headerRow.createCell(6).setCellValue("첨부파일 개수");
			
			List<Board> searchList = bService.boardSearchList(search);
			for(Board board : searchList) {
				Row row = sheet.createRow(rowNo++);
				row.createCell(0).setCellValue(board.getBoardNo());
				row.createCell(1).setCellValue(board.getBoardTypeName());
				row.createCell(2).setCellValue(board.getMemberId());
				row.createCell(3).setCellValue(board.getBoardTitle());
				row.createCell(4).setCellValue(board.getBoardContents());
				row.createCell(5).setCellValue(board.getWriteDate());
				row.createCell(6).setCellValue(board.getBoardFileCount());
			}
			
			response.setContentType("ms-vnd/excel");
	        response.setHeader("Content-Disposition", "attachment;filename=boardlist.xls");
	 
	        workbook.write(response.getOutputStream());
	        workbook.close();
			
		}
	
}
