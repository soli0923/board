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
<style type="text/css">
	.fileDrop{
		width: 100%;
		height: 200px;
		border: 1px dotted red;
	}
	
	small{
		cursor: pointer;
	}

</style>
</head>
<body>

	<div class="fileDrop"></div>
	<div class="uploadedList"></div>

	<script type="text/javascript">
		$(document).ready(function() {
			
			$(".uploadedList").on("click", "small", function(){
				var that = $(this);
				
				$.ajax({
					type : 'post', 
					url : '/deletefile',
					data : {
						filename : $(this).attr('data-ca')
					},
					dataType : 'text',
					success : function(result) {
						alert("삭제되었습니다.");
						that.parent("div").remove();
					}
				});
			});
			
			
			
			
			
			
			$(".fileDrop").on("dragenter dragover", function(event) {
				event.preventDefault();
			});
			$(".fileDrop").on("drop", function(event) {
				event.preventDefault();
				
				var arr = event.originalEvent.dataTransfer.files;
				
				
				
				var file = arr[0];
				
				var formData = new FormData();
				formData.append("file", file);
				
				$.ajax({
					type : 'post',
					url : '/uploadajax',
					data : formData,
					dataType : 'text',
					contentType: false,
					processData: false,
					success : function(data){
						var str='';
						
						if(checkImageType(data)){
							str +="<div><a target='_blank' href='/displayfile?filename="+getOriginalLink(data)+"'><img src='displayfile?filename="+data+"' alt='이미지파일 썸네일입니다.' /></a><p>"+getOriginalName(data)+"</p><small data-ca='"+data+"'>X</small></div>"
						}else{
							str +="<div><a href='/displayfile?filename="+data+"'><img src='/resources/test.png' alt='일반파일 썸네일입니다.'/></a><p>"+getOriginalName(data)+"</p><small data-ca='"+data+"'>X</small></div>"
						}
						
						$(".uploadedList").append(str);
					}
				});
				
				
				
				
			});
		});
		
		
		function getOriginalLink(data){
			var prefix = data.substring(0, 12);
			var suffix = data.substring(14);
			return prefix+suffix;
		}
		
		function getOriginalName(data) {
			var idx = data.lastIndexOf("_")+1;
			return data.substring(idx);
		}
		
		function checkImageType(data) {
			var pattern = /jpg|png|jpeg|gif/i;
			return data.match(pattern);
		}
		
	
	</script>

</body>
</html>