<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인 화면</title>
<link rel="stylesheet" href="<c:url  value='css/bootstrap/css/bootstrap.min.css'/>">
   <script src="<c:url value='js/jquery-3.4.1.min.js' />"></script>
   <script src="<c:url value='css/bootstrap/js/bootstrap.min.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
function jun(){
	  location.href = "<c:url value='junList.do'/>";
}
function suho(){
	  location.href = "<c:url value='suhoList.do'/>";
}
</script>
</head>
<body>
	<div class="container">
		<div class="text-center">
			<h1>인실리코젠 BS팀 Project</h1>

			<div class="panel panel-default">
				<div class="panel-heading">
					<p>버튼을 클릭하시면 해당 페이지로 이동하게 됩니다.</p>
					<button type="button" class="btn btn-success" onclick="jun()">이병준
						홈페이지</button>
					<button type="button" class="btn btn-info" onclick="suho()">이수호
						홈페이지</button>
				</div>
			</div>

		</div>
	</div>
</body>
</html>