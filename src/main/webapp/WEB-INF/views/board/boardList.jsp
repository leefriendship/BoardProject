<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/inc_head.jsp" %>
<!-- board CSS -->
<link rel="stylesheet" href="../../resources/css/board.css">
</head>
<body>
<%@ include file="/WEB-INF/views/include/inc_header.jsp" %>
<div id="conts">
	<h2 class="square-tit mt-40">게시판</h2>
	<button onclick="location.href='/write/boardPage.eansoft';">글쓰기</button>
	<button onclick="location.href='/download/boardList.eansoft';">글목록 다운로드</button>
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
			<c:forEach var="board" items="${bList }">
				<tr>
					<c:url var="bDetail" value="/board/detail.eansoft">
						<c:param name="boardNo" value="${board.boardNo }"></c:param>
					</c:url>
					<td>${board.boardNo }</td>
					<td>${board.boardTypeName }</td>
					<td><a href="${bDetail }">${board.boardTitle }</td>
					<td>${board.member.memberName }</td>
					<td>${board.writeDate }</td>
					<td>${board.boardCount }</td>
					<td>${board.boardFileCount }개</td>
				</tr>
			</c:forEach>
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
	
	<c:if test="${listType eq 'searchList'}">
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
   </script>
</body>
</html>