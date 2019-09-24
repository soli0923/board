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
	<button>click</button>
	<p></p>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("button").on("click", function() {
				var test1 = 'hello';
				var test2 = 'hi';
				var test3 = 'good';
				var test4 = [
					{name : 'john', age : 10, id : 'm001'},
					{name : 'smith', age : 52, id : 'm002'},
					{name : 'ann', age : 7, id : 'm003'}
				];
				
				
				$.ajax({
					type : 'post',
					url : '/resttest3',
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'POST'
					},
					data : JSON.stringify({
						test1 : test1,
						test2 : test2,
						test3 : test3,
						test4 : test4
					}),
					dataType : 'text',
					success : function(result) {
						var arr = JSON.parse(result);
						var obj = arr[1];
						var msg = obj.id;
						$("p").text(msg);
					}
				});
				
			});
		});
	
	</script>





</body>
</html>