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
		
		var arr = ['a', 'b', 'c'];
	
		$(document).ready(function(){
			
			$("button").on("click", function() {
				$.ajax({
					type : "post",
					url : "/test2",
					traditional : true,
					data : {
						'arr' : arr
					},
					dataType : 'text',
					success : function(result) {
						/* var arr2 = eval(result); */
						var arr2 = JSON.parse(result);
						
						$("p").text(arr2[0])
					}
				});
			});
			
			
			
			
			
		});
	
	
	
	</script>
</body>
</html>