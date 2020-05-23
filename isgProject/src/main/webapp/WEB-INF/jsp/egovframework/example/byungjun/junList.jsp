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
	function home() {
		location.href = "<c:url value='/junList.do'/>";
	}
	function add() {
		location.href = "<c:url value='/junMgmt.do'/>";
	}
	function opening() {
		location.href = "<c:url value='/opening.do'/>";
	}
	function excelDownload() {
		location.href = "<c:url value='/junlistExcel.do'/>";
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
		<div class="jumbotron text-center alert alert-success" role="alert"
			onclick="home()">
			<h2>LEE BYUNG JUN</h2>
			<p>㈜인실리코젠 BS팀 Spring Framework Project</p>
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
					<table id="table" class="table table-hover">
						<thead>
							<tr>
								<td style="width: 15%" align="center"><b>게시물 번호</b></td>
								<td style="width: 70%" align="center"><b>제목</b></td>
								<td style="width: 15%" align="center"><b>작성자</b></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${NaverEconomy}"
								varStatus="status">
								<tr>
									<td style="width: 15%" align="center" class="listtd"><c:out
											value="${result.idx}" />&nbsp;</td>

									<td style="width: 70%" align="left" class="listtd"><a
										href="${result.href}"><c:out value="${result.title}" />&nbsp;</a></td>


									<td style="width: 15%" align="center" class="listtd"><c:out
											value="${result.writer}" />&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="text-center">
				<ul class="pagination">
					<c:if test="${paging.prev}">
						<li class="page-item"><a class="page-link"
							href="${paging.makeQuery(paging.startPage - 1)}">이전</a></li>
					</c:if>

					<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
						var="idx">
						<li class="page-item"><a class="page-link"
							href="${paging.makeQuery(idx)}">${idx}</a></li>
					</c:forEach>

					<c:if test="${paging.next && paging.endPage > 0}">
						<li class="page-item"><a class="page-link"
							href="${paging.makeQuery(paging.endPage + 1)}">다음</a></li>
					</c:if>
				</ul>
			</div>

		</div>

		<div class="alert alert-info" role="alert">
			<div class="text-right">
				<button type="button" class="btn btn-info" onclick="opening()">오프닝</button>
				<button type="button" class="btn btn-danger" onclick="add()">등록</button>
				<button type="button" class="btn btn-success" onclick="excelDownload()">엑셀다운로드</button>
			</div>
		</div>
	</div>
</body>
</html>