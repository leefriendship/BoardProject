<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/inc_head.jsp" %>
<!-- board CSS -->
<link rel="stylesheet" href="../../resources/css/board.css?after">
</head>
<body>
<%@ include file="/WEB-INF/views/include/inc_header.jsp" %>
<div id="conts">
	<h2 class="square-tit mt-40">게시판</h2>
	<div class="boardbtn">
	<button  onclick="viewWriteBoardPage()">글쓰기</button>
	<button  onclick="downloadBoardList()">글 목록 다운로드</button>
	<c:if test="${listType eq 'searchList'}">
	<button  onclick="downloadBoardSearchList()">검색한 글 목록 다운로드</button>
	</c:if>
	</div>
	<!-- 검색 -->
   <form class="form--srch" action="/board/searchList.eansoft" method="get">
		<input type="hidden" name="currentPage" value="1"> 
		<input type="hidden" name="listLimit" value="10"> 
		<select name="searchCondition">
			<option value="all">전체</option>
			<option value="title">제목</option>
			<option value="contents">내용</option>
			<option value="writer">작성자</option>
		</select> <input type="text" name="searchValue" placeholder="게시판검색">
		<button type="submit"></button>
	</form>
	<table class="table--basic">
		<colgroup>
            <col style="width:7%;">
            <col style="width:7%;">
            <col style="width:32%;">
            <col style="width:10%;">
            <col style="width:20%;">
            <col style="width:9%;">
            <col style="width:10%;">
        </colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>종류</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>첨부파일</th>
		</thead>
		<tbody>
			<c:if test="${not empty bList}">
			<c:forEach var="board" items="${bList }">
				<tr class="pointer" onclick="viewDetailPage(${board.boardNo })">
					<%-- <c:url var="bDetail" value="/board/detail.eansoft">
						<c:param name="boardNo" value="${board.boardNo }"></c:param>
					</c:url> --%>
					<td>${board.boardNo }</td>
					<td>${board.boardTypeName }</td>
					<td>${board.boardTitle }</td>
					<td>${board.memberId }</td>
					<td>${board.writeDate }</td>
					<td>${board.boardCount }</td>
					<td>${board.boardFileCount }개</td>
				</tr>
			</c:forEach>
			</c:if>
			 <c:if test="${listType eq 'basicList' && empty bList}">
                    <tr>
                    	<td colspan="7" class="t-c"> ${msg } </td>
                    </tr>
             </c:if>
             <c:if test="${listType eq 'searchList' && empty bList}">
                    <tr>
                    	<td colspan="7" class="t-c"> ${msg }</td>
                    </tr>
             </c:if>
             
		</tbody>
	</table>
	
	
	
	
	
	
	
	<c:if test="${listType eq 'basicList'}">
	<div class="btns--paging">
      <button class="fa-solid fa-angles-left first"
      <c:if test="${pi.currentPage > '1' }"> onclick="location.href='/board/list.eansoft?page=${pi.startNavi }'"</c:if>></button>
      <button class="fa-solid fa-angle-left prev"
      <c:if test="${pi.currentPage > '1' }"> onclick="location.href='/board/list.eansoft?page=${pi.currentPage-1 }'"</c:if>></button>
      <c:forEach var="p" begin="${pi.startNavi }" end="${pi.endNavi }">
         <c:url var="pagination" value="/board/list.eansoft">
            <c:param name="page" value="${p }"></c:param>
         </c:url>
         &nbsp;<a href="${pagination }">${p }</a>&nbsp;
      </c:forEach>
      <button class="fa-solid fa-angle-right next"
      <c:if test="${pi.currentPage < pi.endNavi }">onclick="location.href='/board/list.eansoft?page=${pi.currentPage+1 }'"</c:if>></button>
      <button class="fa-solid fa-angles-right last"
      <c:if test="${pi.currentPage < pi.endNavi }">onclick="location.href='/board/list.eansoft?page=${pi.endNavi }'"</c:if>></button>
   </div>
	</c:if>
	
	<c:if test="${listType eq 'searchList' && not empty bList}">
	<div class="btns--paging">
      <button class="fa-solid fa-angles-left first"
      <c:if test="${pi.currentPage > '1' }"> onclick="location.href='/board/searchList.eansoft?page=${pi.startNavi }&searchCondition=${search.searchCondition }&searchValue=${search.searchValue }'"</c:if>></button>
      <button class="fa-solid fa-angle-left prev"
      <c:if test="${pi.currentPage > '1' }"> onclick="location.href='/board/searchList.eansoft?page=${pi.currentPage-1 }&searchCondition=${search.searchCondition }&searchValue=${search.searchValue }'"</c:if>></button>
      <c:forEach var="p" begin="${pi.startNavi }" end="${pi.endNavi }">
      <c:url var="searchPagination" value="/board/searchList.eansoft?searchCondition=${search.searchCondition }&searchValue=${search.searchValue }">
         <c:param name="page" value="${p }"></c:param>
      </c:url>
      &nbsp;<a href="${searchPagination }">${p }</a>&nbsp;
      </c:forEach>
      <button class="fa-solid fa-angle-right next"
      <c:if test="${pi.currentPage < pi.endNavi }">onclick="location.href='/board/searchList.eansoft?page=${pi.currentPage+1 }&searchCondition=${search.searchCondition }&searchValue=${search.searchValue }'"</c:if>></button>
      <button class="fa-solid fa-angles-right last"
      <c:if test="${pi.currentPage < pi.endNavi }">onclick="location.href='/board/searchList.eansoft?page=${pi.endNavi }&searchCondition=${search.searchCondition }&searchValue=${search.searchValue }'"</c:if>></button>
   </div>
	</c:if>
	
	
	
   
   
   </div>
   
   <script>
   // 현재 페이지 하이라이팅
   var pageNo = new URLSearchParams(location.search).get("page");
   if (pageNo != null) {
       $(".btns--paging a:nth-of-type(" + pageNo + ")").addClass("on");
   } else {
       $(".btns--paging a:nth-of-type(1)").addClass("on");
   }
   
   function viewDetailPage(boardNo){
	   var loginId = "<%=(String)session.getAttribute("memberId")%>";
	   if(loginId=="null"){
   		   alert("로그인이 필요합니다.");
   			location.href=("/index.jsp");
   		}else{
   			location.href=("/board/detail.eansoft?boardNo="+boardNo);
   		}
	   } 
   
   function viewWriteBoardPage(){
	   var loginId = "<%=(String)session.getAttribute("memberId")%>";
	   if(loginId=="null"){
   		   alert("로그인이 필요합니다.");
   		}else{
   			location.href=("/write/boardPage.eansoft");
   		}
   }
   
   function downloadBoardList(){
	   var loginId = "<%=(String)session.getAttribute("memberId")%>";
	   if(loginId=="null"){
   		   alert("로그인이 필요합니다.");
   		}else{
   			location.href=("/download/boardList.eansoft");
   		}
   }
   
   function downloadBoardSearchList(){
	   var loginId = "<%=(String)session.getAttribute("memberId")%>";
	   var searchCondition = "${search.searchCondition}";
	   var searchValue = "${search.searchValue}";
	   if(loginId=="null"){
   		   alert("로그인이 필요합니다.");
   		}else{
   			location.href=("/download/boardSearchList.eansoft?searchCondition="+searchCondition+"&searchValue="+searchValue);
   		}
   }
   </script>
</body>
</html>