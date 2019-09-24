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
<script src ="/resources/js/uploadutils.js" type="text/javascript"></script>

<style type="text/css">
	.uploadedList{
		list-style : none;
		margin-bottom : 50px;
	}
	
	.fileDrop{
		width : 80%;
		height : 100px;
		border : 1px dotted red;
		background-color : lightslategray;
		margin : auto;
	}


</style>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="jumbotron">
				<h1>게시글 수정</h1>
			</div>
			
			
			<form>
				<input type="hidden" name="curPage" value="${to.curPage}">
				<input type="hidden" name="perPage" value="${to.perPage}">
				<div class="form-group">
					<label for="bno">글번호</label>
					<input class="form-control" id="bno" name="bno" value="${vo.bno}" readonly="readonly">
				</div>
				
				<div class="form-group">
					<label for="writer">작성자</label>
					<input class="form-control" id="writer" name="writer" value="${vo.writer}">
				</div>
				
				<div class="form-group">
					<label for="title">제목</label>
					<input class="form-control" id="title" name="title" value="${vo.title}">
				</div>
				
				<div class="form-group">
					<label for="content">내용</label>
					<textarea class="form-control" id="content" name="content" rows="3">${vo.content}</textarea>
				</div>
			</form>
			
			<div class="form-group">
				<label>업로드할 파일을 드랍시키세요.</label>
				<div class="fileDrop"></div>
			</div>
			
			<div class="form-group">
				<label>첨부파일</label>
				<ul class="uploadedList clearfix">
					
				</ul>
			</div>
			
			
			<div class="form-group">
				<button class="btn btn-warn modify">수정</button>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var bno = ${vo.bno};
		
		getAllAttach(bno);
	
	
		$(document).ready(function() {
			var $form = $("form");
			$(".modify").on("click", function(event) {
				event.preventDefault();
				
				var str = "";
				$(".delbtn").each(function(index) {
					str += "<input name='files["+index+"]' value='"+$(this).attr("href")+"' type='hidden'/>"
				});
				$form.append(str);
				$form.attr("action", "/board/modify");
				$form.attr("method", "post");
				$form.submit();
			});
			
			
			$(".uploadedList").on("click", ".delbtn", function(event) {
				event.preventDefault();
				var delBtn = $(this);
				var delLi = $(this).parent("div").parent("li");
				
				var delOk = confirm(" 수정 버튼과 상관없이 파일이 즉시 삭제됩니다. \n 삭제하겠습니까?");
				if(delOk){
					$.ajax({
						type : 'post',
						url : '/board/deletefile',
						data : {
							filename : delBtn.attr("href"),
							bno : bno
						},
						dataType : 'text',
						success : function(result) {
							delLi.remove();
						}
					});
					
				}
			});
			
			
			
			
			$(".fileDrop").on("dragenter dragover", function(event) {
				event.preventDefault();
			});
			
			
			
			$(".fileDrop").on("drop", function(event) {
				event.preventDefault();
				var files = event.originalEvent.dataTransfer.files;
				var file = files[0];
				
				var formData = new FormData();
				formData.append("file", file);
				
				$.ajax({
					type : 'post', 
					url : '/uploadajax',
					data : formData,
					dataType : 'text', 
					processData : false,
					contentType : false,
					success : function(filename){
						var str="";
						str = forCode(filename, str);
						
						$(".uploadedList").append(str);
					}
				});
			});
			
			
			
			
			
			
		});
		
		
		function forCode(filename, str) {
			if(checkImageType(filename)){
				str +=""+
				"<li class='col-xs-3'>"+
					"<span><img src='/displayfile?filename="+filename+"' alt='첨부파일입니다.'/></span>"+
					"<div>"+
						"<a href='#'>"+getOriginalName(filename)+"</a>"+
						"<a class='btn btn-default btn-xs delbtn' href='"+filename+"'>"+
							"<span class='glyphicon glyphicon-remove'></span>"+
						"</a>"+
					"</div>"+
				"</li>";
			}else{
				str +=""+
				"<li class='col-xs-3'>"+
					"<span><img src='/resources/test.png' alt='첨부파일입니다.'/></span>"+
					"<div>"+
						"<a href='#'>"+getOriginalName(filename)+"</a>"+
						"<a class='btn btn-default btn-xs delbtn' href='"+filename+"'>"+
							"<span class='glyphicon glyphicon-remove'></span>"+
						"</a>"+
					"</div>"+
				"</li>";
			}
			return str;
		}
		
		
		function getAllAttach(bno) {
			var str = "";
			
			$.getJSON("/board/getattach/"+bno, function(arr) {
				for(var i =0 ; i<arr.length;i++){
					str = forCode(arr[i], str);
				}
				$(".uploadedList").append(str);
			})
				
		}
	
	</script>











</body>
</html>