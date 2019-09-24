<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>


	<c:if test="${not empty login}">
		${login.id} 님, 안녕하세요. <a href="/member/logout">로그아웃</a>
	</c:if>
	<c:if test="${empty login}">
		<a href="/member/login">로그인</a>
	</c:if>
	<br>
	<hr>
		<img alt="" src="/resources/show.jpg">
			<hr>
	<c:forEach items="${list}" var="m">
		<a href="/member/read?id=${m.id}">${m.id} | ${m.name}</a>
		<br>
	</c:forEach>
</body>
</html>