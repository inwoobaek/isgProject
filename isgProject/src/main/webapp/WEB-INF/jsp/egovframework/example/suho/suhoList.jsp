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
	href="<c:url value='/css/bootstrap/css/suhobootstrap.min.css'/>" />
<script src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<script src="<c:url value='/css/bootstrap/js/bootstrap.min.js'/>"></script>

<script type="text/javaScript" language="javascript" defer="defer">
	function add() {
		location.href = "<c:url value='/suhoMgmt.do'/>"
	}
	function view() {
		location.href = "<c:url value='/suhoView.do'/>"
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
		if ($('#user_id').val() == '') {
			alert("아이디를 입력하세요");
			return false;
		}
		if ($('#password').val() == '') {
			alert("비밀번호를 확인해주세요");
			return false;
		}
		return true;
	}
</script>

</head>
<body>

	<div class="container">
		<div class="jumbotron text-center" style="background-color: #63bd69">
			<h2>LEE SU HO</h2>
			<p>㈜인실리코젠 BS팀 Spring Framework Project</p>
		</div>
		<div class="panel panel-default">

			<div class="panel-heading">

				<form class="form-inline" method="post"
					action="<c:url value='/login.do'/>">
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
					<button type="submit" class="btn btn-default"
						onclick="return check()">로그인</button>
				</form>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>게시물 번호</th>
								<th>제목</th>
								<th>등록자</th>
								<th>등록시간</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${newsList}" varStatus="status">
								<tr>
									<td align="center" class="listtd"><c:out
											value="${result.idx}" />&nbsp;</td>
									<td align="left" class="listtd"><a href="${result.href}"
										target="_blank"><c:out value="${result.title}" />&nbsp;</a></td>
									<td align="left" class="listtd"><c:out
											value="${result.writer}" />&nbsp;</td>

									<c:choose>
										<c:when test="${result.newdate != null}">
											<td align="left" class="listtd"><c:out
													value="${result.newdate}" />&nbsp;</td>
										</c:when>
										<c:when test="${result.outdate != null}">
											<td align="left" class="listtd"><c:out
													value="${result.outdate}" />&nbsp;</td>
										</c:when>
									</c:choose>
								</tr>
							</c:forEach>
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
				<button type="button" class="btn btn-default" onclick="">엑셀
					다운로드</button>
			</div>

		</div>

		<div style="text-align: center;">
			<ul class="pagination">
				<c:if test="${pagination.prev}">
					<li><a
						href="${pagination.makeQuery(pagination.startPage - 1)}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<c:forEach begin="${pagination.startPage}"
					end="${pagination.endPage }" var="idx">
					<li><a href="${pagination.makeQuery(idx)}">${idx}</a></li>
				</c:forEach>
				<c:if test="${pagination.next}">
					<li><a href="${pagination.makeQuery(pagination.endPage + 1) }" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">WebSiteName</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#">Page 1</a></li>
				<li><a href="#">Page 2</a></li>
				<li><a href="#">Page 3</a></li>
			</ul>
		</div>
	</nav>
</body>
</html>