<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="kr.co.domain.MemberVO"%>
<%@page import="java.util.List"%>
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
	<%
		List<MemberVO> list = new ArrayList<MemberVO>();
		list.add(new MemberVO("m001", "jane", 7));
		list.add(new MemberVO("m002", "adam", 8));
		list.add(new MemberVO("m003", "tom", 5));
		
		ObjectMapper mapper = new ObjectMapper();
		String list2 = mapper.writeValueAsString(list);
		pageContext.setAttribute("list2", list2);
	%>

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
				var test5 = ${list2};
				
				
				$.ajax({
					type : 'post',
					url : '/resttest4',
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'POST'
					},
					data : JSON.stringify({
						test1 : test1,
						test2 : test2,
						test3 : test3,
						test4 : test4,
						test5 : test5
					}),
					dataType : 'text',
					success : function(result) {
						alert(result);
						var arr = JSON.parse(result);
						var obj = arr[arr.length-1];
						var msg = obj.id;
						$("p").text(msg);
					}
				});
				
			});
		});
	
	</script>





</body>
</html>