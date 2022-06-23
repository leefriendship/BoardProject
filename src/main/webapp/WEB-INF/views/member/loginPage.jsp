<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	<!-- jstl core -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- jstl 함수 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- jstl fmt -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty memberId }">
<h1>로그인</h1>
<form action="/login.ean" method="post" >
<div>아이디 <input type="text" name="memberId"></div>
<div>비밀번호<input type="password" name="memberPwd"></div>
<button type="submit">로그인</button>
</form>
<button type="button" onclick="location.href='/member/joinView.ean'">회원가입</button>
</c:if>
<c:if test="${not empty memberId }">
<h1>${memberName }님 환영합니다.</h1>
<button type="button" onclick="location.href='/logout.ean'">로그아웃</button>
</c:if>

</body>
</html>