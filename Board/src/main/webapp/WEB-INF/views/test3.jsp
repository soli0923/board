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
	<p id="result"></p>
	<button>click</button>
	
	<script type="text/javascript">
		
		$(document).ready(function(){
			
			$("button").on("click", function() {
				$.ajax({
					 type : 'post',
					 url : '/test3',
					 data : {
						 'id' : 'm001',
						 'name' : '홍길동', 
						 'age' : 88
					 },
					
					 dataType : 'text',
					 success : function(result) {
						alert(result);
						var obj = JSON.parse(result);
						$("p").text(obj.id);
					}
					
				});
			});
			
			
			
			
			
		});
	
	
	
	</script>
</body>
</html>