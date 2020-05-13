<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이병준 프로젝트</title>
<link rel="stylesheet"
	href="<c:url  value='css/bootstrap/css/bootstrap.min.css'/>">
<script src="<c:url value='js/jquery-3.4.1.min.js' />"></script>
<script src="<c:url value='css/bootstrap/js/bootstrap.min.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
	function add() {
		location.href = "<c:url value='/junMgmt.do'/>";
	}
	function view() {
		location.href = "<c:url value='/junView.do'/>";
	}
	function opening() {
		location.href = "<c:url value='/opening.do'/>";
	}
	function setPwd(user_id) {
		if (user_id == "admin") {
			$('#password').val('manager');
		} else if (user_id == "guest") {
			$('#password').val('guest');
		} else if (user_id == "guest2") {
			$('$password').val('guest2');
		}
	}
	function check() {
		if ($('#user_id').val() == "") {
			alert("아이디를 입력하세요!");
			return false;
		}
		if ($('#password').val() == "") {
			alert("패스워드를 입력하세요!");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="text-center">
			<h1>이병준 홈페이지</h1>
		</div>

		<div class="alert alert-success" role="alert">
			<form class="form-inline" method="post"
				action="<c:url value='/login.do'/>">
				<div class="form-group">
					<label for="user_id">ID:</label> <select class="form-control"
						id="user_id" name="user_id" onchange="setPwd(this.value);">
						<option value="">선택하세요</option>
						<option value="admin">관리자</option>
						<option value="guest">사용자</option>
						<option value="guest2">사용자2</option>
					</select>
				</div>
				<div class="form-group">
					<label for="password">Password:</label> <input type="password"
						class="form-control" id="password" name="password">
				</div>
				<button type="submit" class="btn btn-success"
					onclick="return check()">로그인</button>
			</form>
		</div>


		<div class="panel panel-default">
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-hover">
						<tr>
							<td style="width: 15%" align="center"><b>게시물 번호</b></td>
							<td style="width: 60%" align="center"><b>제목</b></td>
							<td style="width: 15%" align="center"><b>작성자</b></td>
							<td style="width: 10%" align="center"><b>페이지</b></td>
						</tr>
						<c:forEach var="result" items="${NaverEconomy}" varStatus="status">
								<tr>
									<td style="width: 15%" align="center" class="listtd"><c:out
											value="${result.idx}" />&nbsp;</td>
									<td style="width: 60%" align="left" class="listtd"><a
										href="javascript:view();"><c:out value="${result.title}" />&nbsp;</a></td>
									<td style="width: 15%" align="center" class="listtd"><c:out
											value="${result.writer}" />&nbsp;</td>
									<td style="width: 10%" align="center" class="listtd"><c:out
											value="${result.page}" />&nbsp;</td>
								</tr>
						</c:forEach>
					</table>
				</div>
			</div>

			<div class="text-center">
				<ul class="pagination">
					<li class="page-item "><a class="page-link" href="#">이전</a></li>
					<li class="page-item "><a class="page-link" href="#">1</a></li>
					<li class="page-item "><a class="page-link" href="#">2</a></li>
					<li class="page-item "><a class="page-link" href="#">3</a></li>
					<li class="page-item "><a class="page-link" href="#">4</a></li>
					<li class="page-item "><a class="page-link" href="#">5</a></li>
					<li class="page-item "><a class="page-link" href="#">6</a></li>
					<li class="page-item "><a class="page-link" href="#">7</a></li>
					<li class="page-item "><a class="page-link" href="#">8</a></li>
					<li class="page-item "><a class="page-link" href="#">9</a></li>
					<li class="page-item "><a class="page-link" href="#">10</a></li>
					<li class="page-item "><a class="page-link" href="#">다음</a></li>
				</ul>
			</div>

		</div>

		<div class="alert alert-info" role="alert">
			<div class="text-right">
				<button type="button" class="btn btn-info" onclick="opening()">오프닝</button>
				<button type="button" class="btn btn-danger" onclick="add()">등록</button>
			</div>
		</div>

	</div>
</body>
</html>