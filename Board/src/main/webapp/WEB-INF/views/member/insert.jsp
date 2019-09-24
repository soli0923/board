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
	<h1>회원 가입</h1>
	
	<form action="/member/insert" method="post">
		ID: <input name="id"><button>ID 중복 체크</button>
		<p id="result"></p>
		NAME: <input name="name"><br>
		AGE: <input type="number" name="age"><br>
		<input type="submit">
	</form>


<script type="text/javascript">
	$(document).ready(function() {
		$("button").click(function(event) {
			event.preventDefault();
			var id = $("input[name='id']").val();
			
			$.ajax({
				type: 'post',
				url: '/member/idcheck',
				data:{
					id:id
				},
				dataType: 'text',
				success: function(result) {
					alert(result);
				}
			});
			
			
			
			
			
			
			
			
		});
	});


</script>

</body>
</html>