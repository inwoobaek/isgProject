<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=2">
<link rel="stylesheet"
	href="<c:url value='/css/bootstrap/css/bootstrap.min.css'/>" />
<script src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<script src="<c:url value='/css/bootstrap/js/suhobootstrap.min.js'/>"></script>

<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function() {
		<c:if test = "${!empty msg}">
			alert("${msg}");
		</c:if>
	});
	function add() {
		location.href = "<c:url value='/mgmt.do'/>"
	}
	function view() {
		location.href = "<c:url value='/view.do'/>"
	}
	function setPwd(user_id) {
		if (user_id == "admin") {
			$('#password').val('manager')
		} else if (user_id == "guest") {
			$('#password').val('guest')
		} else if (user_id == "guest2") {
			$('#password').val('guest2')
		}
	}
	function check() {
		if($('#user_id').val() == '') {
			alert("아이디를 입력하세요");
			return false;
		}
		if($('#password').val() == '') {
			alert("비밀번호를 확인해주세요");
			return false;
		}
		return true;
	}
</script>

</head>
<body>

	<div class="container">
	<div class="jumbotron text-center" style="background-color:#63bd69">
			<h2>LEE SU HO</h2>
			<p>㈜인실리코젠 BS팀 Spring Framework Project</p>
		</div>
		<div class="panel panel-default">

			<div class="panel-heading">
			<c:if test="${sessionScope.userId == null || sessionScope.userId =='' }">
				<form class="form-inline" method="post" action="<c:url value='/login.do'/>">
					<div class="form-group">
						<label for="user_id">ID:</label> <select class="form-control"
							id="user_id" name="user_id" onchange="setPwd(this.value);">
							<option value="">안녕하세요</option>
							<option value="admin">관리자</option>	
							<option value="guest">사용자</option>
							<option value="guest2">사용자2</option>
						</select>
					</div>
					<div class="form-group">
						<label for="pwd">Password:</label> <input type="password"
							class="form-control" id="password" name="password">
					</div>
					<div class="checkbox">
						<label><input type="checkbox"> Remember me</label>
					</div>
					<button type="submit" class="btn btn-default" onclick = "return check()">로그인</button>
				</form>
				</c:if>
				<c:if test="${sessionScope.userId != null || sessionScope.userId !='' }">
				  ${sessionScope.userName} = 환영합니다.
				  <button type="button" class="btn btn-default" onclick="out();">로그아웃</button>
				</c:if>
			</div>
			<div class="panel-body">
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
								<td><a href="javascript:view();">1</a></td>
								<td><a href="javascript:view();">안녕하세요</a></td>
								<td>1</td>
								<td>관리자</td>
								<td>2020-05-11</td>
							</tr>
						</tbody>
					</table>
				</div>
				<form class="form-inline" action="/list.do">
					<div class="form-group">
						<label for="id">제목(내용):</label> <input type="text"
							class="form-control" id="searchName">
							<button type="submit" class="btn btn-default">검색</button>
					</div>
				</form>
			</div>
			<div class="panel-footer">
				<button type="button" class="btn btn-default" onclick="add();">등록</button>
			</div>

		</div>
		
		<div style="text-align: center;">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">6</a></li>
				<li><a href="#">7</a></li>
				<li><a href="#">8</a></li>
				<li><a href="#">9</a></li>
				<li><a href="#">10</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</div>
	</div>
</body>
</html>