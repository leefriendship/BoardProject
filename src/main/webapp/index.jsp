<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/inc_head.jsp"%>
<link rel="stylesheet" href="../../resources/css/employee.css">
</head>
<body id="loginPage">
	<%@ include file="/WEB-INF/views/include/inc_header.jsp"%>
<c:if test="${empty memberId }">
<div id="loginDiv">
<p id="logP"class="t-c">Login</p>
<form action="/login.eansoft" method="post" id="loginForm" >
<input type="text" id="memberId" name="memberId" placeholder="아이디를 입력해 주세요." autocomplete="off">
<input type="password" id="memberPwd" name="memberPwd" placeholder="비밀번호를 입력해 주세요.">
<button type="button" onclick="loginChk()">로그인</button>
</form>
<div id="regA" class="t-c">
<a id="myLink1" style="text-decoration:underline" href="/member/joinView.eansoft">회원가입</a>&nbsp;&nbsp;
</div>
</div>
</c:if>

<c:if test="${not empty memberId }">
<div style="margin-top:350px; margin-left:650px;">
<h3 >
<c:if test="${memberPhoto  ne null}">
	<div><img src="../../../resources/uploadFiles/${memberPhoto}" alt="프로필사진"style="width:35px; height:auto; vertical-align: middle;">${memberName }님 환영합니다.</div>
</c:if>
<c:if test="${memberPhoto eq null}">
	<div><img src="../../../resources/img/img_no_profile.png" alt="프로필사진">${memberName }님 환영합니다.</div>
</c:if>
</h3>
<button type="button" onclick="location.href='/logout.eansoft'">로그아웃</button>
</div>
</c:if>

		

<script>

function loginChk() {
    var id = $("#memberId");
    var pwd = $("#memberPwd");
    var form = $("#loginForm");
    if (!id.val()) {
        alert("아이디를 입력해 주십시오.");
        id.focus();
        return;
    }
 
    if (!pwd.val()) {
        alert("비밀번호를 입력해 주십시오.");
        pwd.focus();
        return;
    }
    form.submit();
}


</script>
</body>
</html>