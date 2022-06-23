<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/inc_head.jsp"%>
<!-- board CSS -->
<link rel="stylesheet" href="../../resources/css/board.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/include/inc_header.jsp"%>
	<div id="conts">
		<h2>게시글 작성</h2>
		<div class="subConts page--board-write">
			<form id="multiform" action="/register/board.eansoft" method="post" enctype="multipart/form-data">
				<table>
						<tr>
							<td>게시판 선택</td>
							<td><select name="boardType">
									<option value="1" selected>공지</option>
									<option value="2">유머</option>
									<option value="3">뉴스</option>
							</select></td>
						</tr>
						<tr>
							<td>제목</td>
							<td><input type="text" name="boardTitle"></td>
						</tr>
						<tr>
							<td>첨부파일</td>
							<td><input type="file" name="uploadFiles" multiple
								accept=".png, .jpg, .jpeg,.doc,.docx,.xlsx,.xml,.pptx,.hwp,.pdf,.txt,.zip"
								onchange="checkSize(this)"></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="boardContents"></textarea></td>
						</tr>
				</table>
				<div class="btns-wrap">
					<button type="submit">등록하기</button>
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