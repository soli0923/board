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
<script src="/resources/js/uploadutils.js" type="text/javascript"></script>
<style type="text/css">
	.uploadedList{
		list-style: none;
		margin-bottom: 50px;
	}


</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1>글 자세히 보기</h1>
			
			<form action="">
				<input type="hidden" name="bno" value="${vo.bno}">
				<input type="hidden" name="curPage" value="${to.curPage}">
				<input type="hidden" name="perPage" value="${to.perPage}">
			</form>
			
			<div class="form-group">
				<label for="bno">글번호</label>
				<input class="form-control" id="bno" value="${vo.bno}" readonly="readonly">
			</div>
			
			<div class="form-group">
				<label for="viewcnt">조회수</label>
				<input class="form-control" id="viewcnt" value="${vo.viewcnt}" readonly="readonly">
			</div>
			
			<div class="form-group">
				<label for="title">제목</label>
				<input class="form-control" id="title" value="${vo.title}" readonly="readonly">
			</div>
			
			<div class="form-group">
				<label for="writer">작성자</label>
				<input class="form-control" id="writer" value="${vo.writer}" readonly="readonly">
			</div>
			
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" id="content" readonly="readonly">${vo.content}</textarea>
			</div>
			
			<div class="form-group">
				<label>첨부파일</label>
				<ul class="uploadedList clearfix">
					
				</ul>
			</div>
			
			
			
			<div class="form-group">
				<button id="reply_form" class="btn btn-primary">댓글</button>
				<button  class="btn btn-warning modify">수정</button>
				<button class="btn btn-danger del">삭제</button>
				<button class="btn btn-infor list">목록</button>
			</div>
		</div><!--row  -->

		<div class="row">
			<div id="myCollapsible" class="collapse">
					<hr>
				<div class="form-group">
					<label for="replyer">작성자</label>
					<input id="replyer" class="form-control">
				</div>
				
				<div class="form-group">
					<label for="replytext">내용</label>
					<input id="replytext" class="form-control">
				</div>
				
				<div class="form-group">
					<button id="replyInsertBtn" class="btn btn-default">댓글 등록</button>
					<button id="replyResetBtn" class="btn btn-default">초기화</button>
				</div>
			</div>
		</div><!-- 댓글 입력창  row클래스 div end tag  -->
  		
  		<div id="replies" class="row">
  			
  		</div>
  		
  		<div class="row">
  			<ul class="pagination">
  			
  			</ul>
  		</div>
		
		<div class="row">
			<div data-backdrop="static" class="modal fade" id="myModal">
				<div class="modal-dialog">
					<div class="modal-header">
						<button data-dismiss="modal" class="close">&times;</button>
						<p id="modal_rno"></p>
					</div>
					<div class="modal-body">
						<input id="modal_replytext" class="form-control">
					</div>
					<div class="modal-footer">
						<button id="modal_update" class="btn" data-dismiss="modal">수정</button>
						<button id="modal_delete" class="btn" data-dismiss="modal">삭제</button>
						<button id="modal_close" class="btn" data-dismiss="modal">닫기</button>
					</div>
				
				</div>
			</div>
		</div>
		
	</div><!--container  -->
	
	<script type="text/javascript">
		var bno = ${vo.bno};
		var replyPage = 1;
		
		$(document).ready(function() {
			
			getAllAttach(bno);
			
			$(".pagination").on("click", "li a", function(event) {
				event.preventDefault();
				replyPage = $(this).attr("href");
				getAllList(bno, replyPage);
			});
			
			
			
			$("#modal_delete").click(function() {
				var rno = $("#modal_rno").text();
				
				$.ajax({
					type : 'delete',
					url : "/replies/"+rno,
					headers : {
						"Content-Type": "application/json",
						"X-HTTP-Method-Override" : "DELETE"
					},
					dataType:'text',
					success : function(result) {
						alert(result);
						getAllList(bno, replyPage);
					}
				});
			});
			
			
			$("#modal_update").click(function() {
				var rno = $("#modal_rno").text();
				var replytext = $("#modal_replytext").val();
				
				$.ajax({
					type : 'put',
					url : '/replies/'+rno,
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "PUT"
					},
					data : JSON.stringify({
						replytext : replytext
					}),
					dataType : 'text', 
					success : function(result) {
						alert(result);
						getAllList(bno, replyPage);
					}
				});
			});
			
			
			
			
			$("#replies").on("click", ".callModal", function() {
				var rno = $(this).prev("p").attr("data-rno");
				var replytext = $(this).prev("p").text();
				
				$("#modal_rno").text(rno);
				$("#modal_replytext").val(replytext);
				
				$("#myModal").modal("show");
			});
			
			
		
			
			
			$("#reply_form").click(function() {
				$("#myCollapsible").collapse("toggle");
			});
			
			
			$("#replyResetBtn").click(function() {
				$("#replyer").val("");
				$("#replytext").val("");
			});
			
			
			$("#replyInsertBtn").click(function() {
				
				var replyer = $("#replyer").val();
				var replytext = $("#replytext").val();
				
				$.ajax({
					type : 'post',
					url : '/replies',
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'post'
					},
					data : JSON.stringify({
						bno : bno,
						replyer:replyer,
					    replytext:replytext
					}),
					dataType: 'text',
					success : function(result) {
						alert(result);
						if(result == 'INSERT_SUCCESS'){
							$("#replyer").val("");
							$("#replytext").val("");
							getAllList(bno, replyPage);
						}
						
					}
				});
			});
			
			
			
			
			
			var $form = $("form");
			
			$(".modify").click(function() {
				$form.attr("action", "/board/modify");
				$form.attr("method", "get");
				$form.submit();
			});
			
			$(".del").click(function(event) {
				event.preventDefault();
				
				$(".uploadedList li").each(function() {
					var filename = $(this).attr("data-ca");
					$.ajax({
						type : 'post',
						url : '/deletefile',
						data : {
							filename : filename
						},
						dataType : 'text'
						
					});
				});
				
			 	$form.attr("action", "/board/del");
				$form.attr("method", "post");
				$form.submit(); 
			});
			
			$(".list").click(function() {
				$form.attr("action", "/board/list");
				$form.attr("method", "get");
				$form.submit();
			});
			
			
			getAllList(bno, replyPage);
			
		});
	
		
		function getAllList(bno, replyPage) {
			
			$.getJSON("/replies/"+bno+"/"+replyPage, function(result) {
				var str='<hr>';
				var arr = result.list;
				console.log(result);
				 
				for(var i=0;i<arr.length;i++){
					str+='<div class="panel panel-info">'+
  				'<div class="panel-heading">'+
  					'<span>rno: '+arr[i].rno+', 작성자: <span class="glyphicon glyphicon-user"></span> '+arr[i].replyer+'</span>'+
  					'<span class="pull-right"><span class="glyphicon glyphicon-time"></span> '+arr[i].updatedate+'</span>'+
  				'</div>'+
  				
  				'<div class="panel-body">'+
  					'<p data-rno="'+arr[i].rno+'">'+arr[i].replytext+'</p>'+
  					'<button class="btn callModal"><span class="glyphicon glyphicon-edit"></span>수정/삭제<span class="glyphicon glyphicon-trash"></span></button>'+
  				'</div>'+
  			'</div>';
				}
				
				$("#replies").html(str);
				
				printPaging(result);
				
			});
		}
		
		function printPaging(to) {
			var str='';
			
			if(to.curPage>1){
				str+="<li><a href='"+(to.curPage-1)+"'>&laquo;</a></li>"
			}
			
			for(var i=to.bpn;i<to.spn+1;i++){
				var strClass = to.curPage == i ? 'active':'';
				str+="<li class='"+strClass+"'><a href='"+i+"'>"+i+"</a></li>";
			}
			
			if(to.curPage<to.totalPage){
				str+="<li><a href='"+(to.curPage+1)+"'>&raquo;</a></li>"
			}
			
			$(".pagination").html(str);
			
		}
		
		
		function getAllAttach(bno){
			$.getJSON("/board/getattach/"+bno, function(arr) {
				var str = "";
				for(var i =0 ; i<arr.length;i++){
					if(checkImageType(arr[i])){
						str += ""+
					"<li data-ca='"+arr[i]+"' class='col-xs-3'>"+
						"<span><img alt='첨부파일입니다.' src='/displayfile?filename="+arr[i]+"'></span>"+
						"<div>"+
							"<a target='blank' href='/displayfile?filename="+getOriginalLink(arr[i])+"'>"+getOriginalName(arr[i])+"</a>"+
						"</div>"+
					"</li>";
					}else{
						str += ""+
					"<li data-ca='"+arr[i]+"' class='col-xs-3'>"+
						"<span><img alt='첨부파일입니다.' src='/resources/test.png'></span>"+
						"<div>"+
							"<a href='/displayfile?filename="+getOriginalLink(arr[i])+"'>"+getOriginalName(arr[i])+"</a>"+
						"</div>"+
					"</li>";
					}/* else의 종료 태그 */
				}/* for문의 종료 태그 */
				$(".uploadedList").append(str);
			});
		}
	
		
	
	</script>
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>