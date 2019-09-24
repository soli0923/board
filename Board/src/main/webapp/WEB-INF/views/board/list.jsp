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
	<div class="container">
		<div class="row">
			<div class="jumbotron">
				<h1>게시글 목록</h1>
			</div>
		</div>
	
		<div class="row">
			<div class="col-xs-push-8 col-xs-4">
			<select id="perPageSel" class="form-control">
				<option disabled>페이지당 글 수</option>
				<option ${to.perPage == 5? 'selected':''}>5</option>
				<option ${to.perPage == 10? 'selected':''} >10</option>
				<option ${to.perPage == 20? 'selected':''} >20</option>
			</select>
			</div>
		</div>
	
	
		<div class="row">
			
			<a href="/board/create">글쓰기</a>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				
				
				<tbody>
					<c:forEach items="${to.list}" var="vo">
						<tr>
							<td>${vo.bno}</td>
							<td><a href="/board/read?bno=${vo.bno}&curPage=${to.curPage}&perPage=${to.perPage}">${vo.title}</a></td>
							<td>${vo.writer}</td>
							<td>${vo.updatedate}</td>
							<td>${vo.viewcnt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="row text-center">
			<ul class="pagination">
			
				<c:if test="${to.curPage>1}">
					<li><a href="/board/list?curPage=${to.curPage-1}&perPage=${to.perPage}">&laquo;</a></li>
				</c:if>
			
				<c:forEach var="idx" begin="${to.bpn}" end="${to.spn}">
					<li class="${to.curPage == idx?'active':''}"><a href="/board/list?curPage=${idx}&perPage=${to.perPage}">${idx}</a></li>
				</c:forEach>
				
				
				<c:if test="${to.curPage<to.totalPage}">
					<li><a href="/board/list?curPage=${to.curPage+1}&perPage=${to.perPage}">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
		
		<div class="row">
			<div class="input-group">
				<span class="input-group-addon">
					<select id="searchSel">
						<option disabled>검색 기준</option>
						<option value="writer">작성자</option>
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>	
				</span>
				
				<input class="form-control" id="keyword">
				
				<span class="input-group-btn">
					<button id="searchBtn" class="btn btn-info">검색</button>
				</span>
				
			</div>		
		</div>
		
		
		
		<hr>
		
	</div>
	
	
	
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#searchBtn").click(function() {
				var searchType = $("#searchSel option:selected").val();
				var keyword = $("#keyword").val();
				var url = "/sboard/list?searchType="+searchType+"&keyword="+keyword;
				window.open(url);
			});
			
			
			
			
			
			
			
			
			
			
			$("#perPageSel").change(function() {
				var perPage = $("#perPageSel option:selected").val();
				
				$.ajax({
					type: 'get',
					url: '/board/amount/'+perPage,
					
					dataType:'text',
					success: function(totalPage) {
						if(${to.curPage}>totalPage){
							location.assign("/board/list?curPage="+totalPage+"&perPage="+perPage);
						}else{
							location.assign("/board/list?curPage=${to.curPage}&perPage="+perPage);
						}
					}
				});
				
			});
		});
	
	
	</script>
	
	
	
	

</body>
</html>