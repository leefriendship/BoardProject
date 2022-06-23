<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/inc_head.jsp"%>
<!-- board CSS -->
<link rel="stylesheet" href="../../resources/css/board.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/include/inc_header.jsp"%>
	<div id="conts">
		<h2>게시글 수정</h2>
<div  class="subConts page--board-write">
					<form action="/modify/board.eansoft" method="post" enctype="multipart/form-data">
						<input type="hidden" value="1" name="boardType">
						<input type="hidden" value="${board.boardNo }" name="boardNo">
						<table>
							<tr>
								<td>제목</td>
								<td><input type="text" name="boardTitle" value="${board.boardTitle }"></td>
							</tr>
							<tr>
								<td>작성자</td>
								<td><input type="text" value="${board.member.memberName }" readonly></td>
							</tr>
							<tr>
								<td>내용</td>
								<td><textarea name="boardContents" >${board.boardContents}</textarea>
								</td>
							</tr>
							<tr>
								<td>첨부파일</td>
								<td>
									<input type="file" name="reloadFiles" multiple accept=".png, .jpg, .jpeg,.doc,.docx,.xlsx,.xml,.pptx,.hwp,.pdf,.txt,.zip" onchange="checkSize(this)" ></div>
									<c:forEach var="file" items="${board.fList}">
									<div>${file.fileName}</div>
									</c:forEach>
								</td>
							</tr>
						</table>
						<div class="btns-wrap">
							<button class="point" type="submit">수정하기</button>
						</div>
					</form>
				</div>

	</div>
	<script>
//첨부파일 사이즈 3MB로 제한
function checkSize(input){
	if(input.files && input.files[0]){
		var maxSize = 3 * 1024 * 1024;
		var fileSize = input.files[0].size;
		
		if(fileSize >  maxSize){
		alert("첨부파일 사이즈는 3MB 이내로 등록 가능합니다.");
		input.value = null;
		}
	}
}

</script>
</body>
</html>