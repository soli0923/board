<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<div class="container">
		<div class="row">
			<c:if test="${not empty err_login}">
				<p style="color: red">${err_login}</p>
			</c:if>
			<form action="/user/loginpost" method="post">
				<div class="form-group">
					<label id="id">아이디</label>
					<input name="id" id="id" class="form-control">
				</div>
				
				<div class="form-group">
					<label id="pw">비밀번호</label>
					<input name="pw" id="pw" class="form-control">
				</div>
				
				<div class="form-group">
					<input value="로그인" type="submit" class="form-control btn btn-primary">
				</div>
			</form>
		</div>
	</div>
</body>
</html>