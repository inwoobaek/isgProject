<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	/**
	* @Class Name : junView.jsp
	* @Description : 상세 화면(미완성)
	* @Modification Information
	*
	*   수정일                수정자                   수정내용
	*  -------    --------    ---------------------------
	*  2020.05.27   이병준                 코멘트 작성
	*
	* author 인실리코젠 BS팀
	* since 2020.05.27
	*
	*/
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 제목 -->
<title>이병준 프로젝트</title>
<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="<c:url  value='css/bootstrap/css/bootstrap.min.css'/>">
<script src="<c:url value="/js/jquery-3.3.1.min.js" />"></script>
<Script src="<c:url value='js/bootstrap.min.js'/>"></script>
<!-- JS -->
<script type="text/javaScript" language="javascript" defer="defer">
	/* 리스트 페이지 */
	function list() {
		location.href = "<c:url value='/junList.do'/>";
	}
</script>
</head>
<body>
	<div class="container">
		<!-- 배너 -->
		<div class="pannel pannel-primary">
			<div class="jumbotron text-center alert alert-success" role="alert">
				<h2>상세화면</h2>
				<p>해당 페이지는 상세화면 입니다.</p>
			</div>
			<!-- Table -->
			<div class="table table-responsive">
				<table class="table">
					<tr>
						<th class="success">글번호</th>
						<td>글번호</td>
						<th class="success">조회수</th>
						<td>조회수</td>
					</tr>
					<tr>
						<th class="success">작성자</th>
						<td>작성자</td>
						<th class="success">작성일</th>
						<td>작성일</td>
					</tr>
					<tr>
						<th class="success">제목</th>
						<td colspan="3">제목</td>
					</tr>
					<tr>
						<th class="success">글 내용</th>
						<td colspan="3">글 내용</td>
					</tr>
				</table>
				<!-- Button -->
				<div class="alert alert-info" role="alert">
					<div class="text-right">
						<button type="button" class="btn btn-warning">수정</button>
						<button type="button" class="btn btn-danger">삭제</button>
						<button type="button" class="btn btn-info" onclick="list()">목록</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>