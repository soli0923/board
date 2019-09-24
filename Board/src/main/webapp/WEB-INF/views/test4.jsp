<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
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
		
		List<MemberVO> list = (List<MemberVO>)request.getAttribute("list");
		ObjectMapper mapper = new ObjectMapper();
		
		String str = mapper.writeValueAsString(list);
		pageContext.setAttribute("list", str);
		
	%>
	<p></p>
	<button>click</button>


<script>

var StringBuffer = function() {
    this.buffer = new Array();
};
StringBuffer.prototype.append = function(str) {
    this.buffer[this.buffer.length] = str;
};
StringBuffer.prototype.toString = function() {
    return this.buffer.join("");
};

</script>



<script type="text/javascript">

	$(document).ready(function() {
		$("button").on("click", function() {
			$.ajax({
				type : 'post',
				url : "/test4",
				data : {
					list : JSON.stringify(${list})
				},
				dataType: 'text',
				success : function(result) {
					var obj = JSON.parse(result);
					var sb=new StringBuffer();
					for(var i=0;i<obj.length;i++){
						sb.append("<a href='");
						sb.append(obj[i].id);
						sb.append("'>");
						sb.append(obj[i].id);
						sb.append(":");
						sb.append(obj[i].name);
						sb.append(":");
						sb.append(obj[i].age);
						sb.append("</a>");
						sb.append("<br>");
					}
					
					$("p").append(sb.toString());
				}
			});
		});
	});




</script>




</body>
</html>