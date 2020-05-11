<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이수호 프로젝트</title>
   <link rel="stylesheet" href="<c:url  value='css/bootstrap/css/bootstrap.min.css'/>">
   <script src="<c:url value='js/jquery-3.4.1.min.js' />"></script>
   <script src="<c:url value='css/bootstrap/js/bootstrap.min.js'/>"></script>
</head>
<body> 

	<div calss="container">
		<h1>메인화면</h1>
		<div class="panel panel-default">

			<div class="panel-heading">
				<form class="form-inline" action="/login.do">
					<div class="form-group">
						<label for="id">ID:</label> <input type="text"
							class="form-control" id="id">
					</div>
					<div class="form-group">
						<label for="pwd">Password:</label> <input type="password"
							class="form-control" id="pwd">
					</div>
					<div class="checkbox">
						<label><input type="checkbox"> Remember me</label>
					</div>
					<button type="submit" class="btn btn-default">로그인</button>
				</form>
			</div>
			<div class="panel-body">
				<form class="form-inline" action="/list.do">
					<div class="form-group">
						<label for="id">제목(내용):</label> <input type="text"
							class="form-control" id="searchName">
					</div>
		
					<button type="submit" class="btn btn-default">검색</button>
				</form>

				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>게시물 번호</th>
								<th>제목</th>
								<th>조회수</th>
								<th>등록자</th>
								<th>등록일</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>안녕하세요</td>
								<td>1</td>
								<td>관리자</td>
								<td>2020-05-11</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="panel-footer">
			<button type="button" class="btn btn-default"
			onclick="add();">등록</button>
			</div>
			
		</div>
	</div>
</body>
</html>