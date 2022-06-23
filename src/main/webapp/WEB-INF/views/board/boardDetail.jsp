<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/inc_head.jsp"%>
<!-- board CSS -->
<link rel="stylesheet" href="../../resources/css/board.css?after">
</head>
<body>
	<%@ include file="/WEB-INF/views/include/inc_header.jsp"%>
	<div id="conts">
		<div id="boardTop">
			<div class="board-btn-div">
				<c:url var="bModify" value="/board/modifyPage.eansoft">
					<c:param name="boardNo" value="${board.boardNo}"></c:param>
				</c:url>
				<c:url var="bDelete" value="/remove/board.eansoft">
					<c:param name="boardNo" value="${board.boardNo} "></c:param>
				</c:url>
				<c:if test="${board.memberId  eq memberId }">
					<button class="board-detail-btn" type="button"
						style='color: rgb(192, 57, 43);' onclick="openAlert(this);">삭제하기</button>
					<section class="section--alert">
						<div class="bg-black"></div>
						<!-- 검은배경 필요할 경우, 필요없으면 이 태그 통째로 지우기 -->
						<div class="section--alert__conts">
							<button class="btn--close" type="button"></button>
							<p>
								확인을 누르시면<br> 게시글이 삭제됩니다. 삭제하시겠습니까?
							</p>

							<div class="btns-wrap mt-20">

								<button class="point" type="button"
									onclick="location.href='${bDelete }'">확인</button>
								<button class="finished closeWindow" type="button">닫기</button>
							</div>
						</div>
					</section>
					<button class="board-detail-btn"
						onclick="location.href='${bModify }'">수정하기</button>
				</c:if>
			</div>

			<div id="title-div">${board.boardTitle }</div>
			<c:if test="${board.member.memberPhoto ne null}">
				<div class="write-div">
					<img
						src="../../../resources/uploadFiles/${board.member.memberPhoto}"
						alt="프로필사진"
						style="width: 30px; height: auto; vertical-align: middle;">${board.member.memberName }</div>
			</c:if>
			<c:if test="${board.member.memberPhoto eq null}">
				<div class="write-div">
					<img src="../../../resources/img/img_no_profile.png" alt="프로필사진"
						style="width: 30px; height: auto; vertical-align: middle;">${board.member.memberName }</div>
			</c:if>
			<div class="write-div">${board.writeDate }</div>
			<div id="attached-file-div">
				<c:forEach var="file" items="${board.fList}">
					<div>
						<a href="../../../resources/uploadFiles/${file.fileRename }"
							download>${file.fileName}</a>
					</div>
				</c:forEach>
				<c:if test="${empty board.fList}">
					<p>등록된 파일이 없습니다.</p>
				</c:if>
			</div>
			<div id="board-contents-div">${board.boardContents }</div>
			<div id="board-bottom-div">조회수 ${board.boardCount }&nbsp&nbsp&nbsp&nbsp</div>
		</div>
		<div id="reply-div">					
			<div>
				<span>${sessionScope.memberName}</span>
				<textarea id="rContents" maxlength="500" placeholder="댓글을 입력해 주세요."></textarea>
			</div>
			<div><button id="rSubmit">댓글 작성</button></div>
		</div>
		<table class="reply-table" id="rtb">
			<thead>
				<!-- <tr>
					<td><b id="rCount"></b></td>
				</tr> -->
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>	
		
		<script>
function openAlert(alertWindow) {
    $(alertWindow).siblings('.section--alert').css('display', 'flex');
}

/* 모달 및 팝업 닫기 */
$('.btn--close').on('click', function () {
    $(this).parent().parent().stop().fadeOut(100);
});

$(document).on('click', '.closeWindow', function () {
    $(this).parent().parent().parent().stop().fadeOut(100);
});

$('.bg-black').on('click', function () {
    $(this).parent().stop().fadeOut(100);
});

$('body,html').keydown(function (e) {
    if (e.keyCode == 27 || e.which == 27) {
        if ($('.section--modal').css('display') == 'flex') {
            $('.section--modal').fadeOut(200);
        } else if ($('.section--alert').css('display') == 'flex') {
            $('.section--alert').fadeOut(200);
        }

    }
})
/* //모달 및 팝업 닫기 */
 
 
 getReplyList();
	
		
		$("#rSubmit").on("click", function(){
			var boardNo = "${board.boardNo}";
			var replyContents = $("#rContents").val();
			$.ajax({
				url : "/reply/add.eansoft",
				type : "post",
				data : {"boardNo" : boardNo, "replyContents" : replyContents},
				success : function(data){
					if(data == "success"){
						alert("댓글등록성공");
						$("#reply-div textarea").val("");
						getReplyList();
					}else{
						alert("댓글 등록 실패");
					}
				},
				error : function(){
					alert("댓글 등록 과정에서 오류가 발생했습니다.");	
				}
			});
		});
	
		function getReplyList() {
			var boardNo = "${board.boardNo}";
			$.ajax({
				url  : "/reply/list.eansoft",
				type : "get",
				data : { "boardNo" : boardNo },
				dataType : "json",
				success : function(data) { 
					var count = data.length;
					var $boardDiv = $("#boardTop");
					var $tableBody = $("#rtb tbody");
					$tableBody.html(""); 
					var $trCount = $("<tr>");
					
					var $tdCount = $("<div id='board-bottom-div2'>").html("&nbsp&nbsp&nbsp&nbsp<img src='../../../../resources/images/icons/reply.png' style='width:15px; height:auto; vertical-align: middle;'/>&nbsp&nbsp<b>댓글  " + count + "개</b></div>");
					
					
					
					$trCount.append($tdCount);
					$tableBody.append($trCount);
					$boardDiv.append($trCount);
					for(var i = 0; i < data.length; i++) {
						var $tr = $("<tr>");

						var $br = $("<br>");
						var $rWriter 	 = $("<td width='160'><b>").text(data[i].memberId).append("</b>");
						var $reWriter 	 = $("<td width='160' id='reWriter'><img src='../../../../resources/img/rereply2.png' style='width:20px; height:auto; vertical-align: middle; align :right;'/><b>"+data[i].memberId+"</b>");
						var $rContent 	 = $("<td width='250' colspan='2' class='rContent'>").text(data[i].replyContents);
						var $deleteMsg 	 = $("<td width='250' colspan='4' class='rContent'>").text('삭제된 댓글 입니다.');
						var $reContent 	 = $("<td width='250' colspan='2' class='rContent' >").text(data[i].replyContents);
						var $rCreateDate = $("<td class='t-c' width='150'>").text(data[i].writeDate);
						var $btnArea 	 = $("<td class='t-c' width='150'>")
											.append("<a href='javascript:void(0)' onclick='modReplyView(this, "+data[i].replyNo+", \""+data[i].replyContents+"\");'>수정</a> ")
											.append("<a href='javascript:void(0)' onclick='removeReply("+data[i].replyNo+");'>삭제</a>")

							
						var $btnReReply	 = $("<td class='t-c' width='100'>").append("<a href='javascript:void(0)' onclick='ReReplyWriteView(this, "+data[i].replyNo+", \""+data[i].replyContents+"\");'>답글</a>");
						
						if(data[i].replyOrder == 0){
							if(data[i].replyStatus=='N')
								{
								$tr.append($deleteMsg);
								$tableBody.append($tr);
								}else{
							$tr.append($rWriter);
							$tr.append($rContent);
							$tr.append($rCreateDate);
							$tr.append($btnArea);
							$tr.append($btnReReply);
							$tableBody.append($tr);
								}
						}else{
							if(data[i].replyStatus=='N')
							{
							$tr.append($deleteMsg);
							$tableBody.append($tr);
							}else{
							$tr.append($reWriter);
							$tr.append($reContent);
							$tr.append($rCreateDate);
							$tr.append($btnArea);
							$tr.append("<td></td>");
							$tableBody.append($tr);
							}
						}

					}
				},
				error   : function() { 
					var $tableBody = $("#rtb tbody");
					$tableBody.html(""); 
					var $trCount = $("<tr>");
					var $trMsg = $("<tr>");
					var $tdCount = $("<td colspan='4'>").html("<b>댓글 (0)</b>");
					var $tdMsg = $("<td colspan='4'>").text("댓글이 없습니다.");
					$trCount.append($tdCount);
					$trMsg.append($tdMsg);
					$tableBody.append($trCount);
					$tableBody.append($trMsg);
				}
			});
		}
		
		function removeReply(replyNo) {
			var answer = confirm("정말 삭제하시겠습니까?"); 
			if (answer) { 
			$.ajax({
				url  : "/reply/delete.eansoft",
				type : "get",
				data : { "replyNo" : replyNo },
				success : function(data) {
					if(data == "success") {
						getReplyList();
					}else{
						alert("댓글 삭제 실패!");
					}
				},
				error 	: function() {
					alert("Ajax 통신 실패");
				}
			});
			}
		}
		
		function modReplyView(obj, replyNo, replyContents) {
			var $trModify = $("<tr>");
			var $tdModify = $("<td colspan='3'>");
			var $tdModifyBtn = $("<td>");
			
			$tdModify.append("<input type='text' size='50' value='"+replyContents+"' id='modifyData'>");
			$tdModifyBtn.append("<button onclick='modReply("+replyNo+");'>수정완료</button>");
			$trModify.append($tdModify);
			$trModify.append($tdModifyBtn);
			$(obj).parent().parent().after($trModify);
		}
		
		   function modReply(replyNo) {
			var modifiedData = $("#modifyData").val();
			$.ajax({
				url : "/reply/modify.eansoft",
				type : "post",
				data : { "replyNo" : replyNo, "replyContents" : modifiedData },
				success : function(data) {
					if(data == "success") {
						getReplyList();
					}else{
						alert("댓글 수정 실패");
					}
				},
				error : function() {
					alert("Ajax 통신 실패");
				}
			})
		} 
		

		   function ReReplyWriteView(obj, parentReplyNo, replyContents) {
			    var $trReReply = $("<tr>");
				var $tdReReply = $("<td colspan='3'>");
				var $tdReReplyBtn = $("<td>");
				
				$tdReReply.append("<input type='text' size='50' id='reReplyData'>");
				$tdReReplyBtn.append("<button onclick='insertReReply("+parentReplyNo+");'>등록</button>");
				$trReReply.append($tdReReply);
				$trReReply.append($tdReReplyBtn);
				$(obj).parent().parent().after($trReReply);
			
			}
		   
		   function insertReReply(parentReplyNo) {
			   var boardNo = "${board.boardNo}";
			   var replyContents = $("#reReplyData").val();
				$.ajax({
					url : "/register/reReply.eansoft",
					type : "post",
					data : { "parentReplyNo" : parentReplyNo, "replyContents" : replyContents, "boardNo" : boardNo},
					success : function(data) {
						if(data == "success") {
							alert("등록 성공");
							getReplyList();
						}else{
							alert("등록 실패");

						}
					},
					error : function() {
						alert("Ajax 통신 실패");
					}

				})
			} 
 
 
</script>
</body>
</html>