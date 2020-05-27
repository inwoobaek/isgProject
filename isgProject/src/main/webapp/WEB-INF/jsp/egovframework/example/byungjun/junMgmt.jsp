<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	/**
	* @Class Name : junMgmt.jsp
	* @Description : 등록 화면(미완성)
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
<!-- 타이틀 -->
<title>이병준 프로젝트</title>
<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="<c:url  value='css/bootstrap/css/bootstrap.min.css'/>">
<script src="<c:url value="/js/jquery-3.3.1.min.js" />"></script>
<Script src="<c:url value='js/bootstrap.min.js'/>"></script>
<!-- JS -->
<script type="text/javaScript" language="javascript" defer="defer">
	/* 리스트 페이지 */
	function cancel() {
		location.href = "<c:url value='/junList.do'/>";
	}
</script>
</head>

<body>
	<div class="container">
		<!-- 배너 -->
		<div class="text-center">
			<h1>등록 & 수정</h1>
		</div>
		<!-- 부제 -->
		<div class="alert alert-success" role="alert">
			<p>해당 페이지는 등록화면 입니다.</p>
		</div>
		<!-- 등록 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-horizontal" method="post" action="">
					<div class="form-group">
						<label class="control-label col-sm-2" for="idx">게시물아이디:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="idx" name="idx"
								placeholder="자동발번">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">제목:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="title" name="title"
								placeholder="제목을 입력하세요" maxlength="100">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">등록자/등록일:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="writer" name="writer"
								placeholder="등록자를 입력하세요" maxlength="15"
								style="float: left; width: 30%"> <input type="text"
								class="form-control" id="indate" name="indate"
								placeholder="등록일을 입력하세요" maxlength="10"
								style="float: left; width: 30%">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">내용:</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="5" id="contents"
								name="contents" maxlength="1000"></textarea>
						</div>
					</div>

				</form>
			</div>
		</div>
		<!-- Button -->
		<div class="alert alert-info" role="alert">
			<div class="text-right">
				<button type="button" class="btn btn-danger">등록</button>
				<button type="button" class="btn btn-warning">수정</button>
				<button type="button" class="btn btn-info" onclick="cancel()">취소</button>
			</div>
		</div>

	</div>

</body>
</html>