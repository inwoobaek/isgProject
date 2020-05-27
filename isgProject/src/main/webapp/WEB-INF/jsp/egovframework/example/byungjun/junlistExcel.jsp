<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	/**
	* @Class Name : junlistExcel.jsp
	* @Description : Excel 다운로드 화면
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 타이틀 -->
<title>이병준 프로젝트</title>
<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="<c:url  value='css/bootstrap/css/bootstrap.min.css'/>">
<script src="<c:url value='js/jquery-3.4.1.min.js' />"></script>
<script src="<c:url value='css/bootstrap/js/bootstrap.min.js'/>"></script>
<!-- JS -->
<script type="text/javaScript" language="javascript" defer="defer">
	/* 리스트 페이지 */
	function home() {
		location.href = "<c:url value='/junList.do'/>";
	}
	/* 테이블 엑셀화 */
	function fnExcelReport(id, title) {
		var tab_text = '<html xmlns:x="urn:schemas-microsoft-com:office:excel">';
		tab_text = tab_text
				+ '<head><meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';
		tab_text = tab_text
				+ '<xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>'
		tab_text = tab_text + '<x:Name>Test Sheet</x:Name>';
		tab_text = tab_text
				+ '<x:WorksheetOptions><x:Panes></x:Panes></x:WorksheetOptions></x:ExcelWorksheet>';
		tab_text = tab_text
				+ '</x:ExcelWorksheets></x:ExcelWorkbook></xml></head><body>';
		tab_text = tab_text + "<table border='1px'>";
		var exportTable = $('#' + id).clone();
		exportTable.find('input').each(function(index, elem) {
			$(elem).remove();
		});
		tab_text = tab_text + exportTable.html();
		tab_text = tab_text + '</table></body></html>';
		var data_type = 'data:application/vnd.ms-excel';
		var ua = window.navigator.userAgent;
		var msie = ua.indexOf("MSIE ");
		var fileName = "네이버 뉴스 경제일반" + '.xls';
		//Explorer 환경에서 다운로드
		if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
			if (window.navigator.msSaveBlob) {
				var blob = new Blob([ tab_text ], {
					type : "application/csv;charset=utf-8;"
				});
				navigator.msSaveBlob(blob, fileName);
			}
		} else {
			var blob2 = new Blob([ tab_text ], {
				type : "application/csv;charset=utf-8;"
			});
			var filename = fileName;
			var elem = window.document.createElement('a');
			elem.href = window.URL.createObjectURL(blob2);
			elem.download = filename;
			document.body.appendChild(elem);
			elem.click();
			document.body.removeChild(elem);
		}
	}
</script>
</head>
<body>
	<div class="container">
		<!-- 배너 -->
		<div class="pannel pannel-primary">
			<div class="jumbotron text-center alert alert-success" role="alert">
				<h2>엑셀변환 화면</h2>
				<p>엑셀 다운로드 버튼을 눌러주세요. [최근 기사 100개가 엑셀화 됩니다]</p>
			</div>
			<!-- Button -->
			<div class="alert alert-info" role="alert">
				<div class="text-right">
					<button type="button" class="btn btn-danger" onclick="home()">홈</button>
					<button type="button" class="btn btn-success"
						onclick="fnExcelReport('table','title');">엑셀다운로드</button>
				</div>
			</div>
			<!-- List -->
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="table-responsive">
						<table id="table" class="table table-hover">
							<thead>
								<tr>
									<td style="width: 15%" align="center"><b>게시물 번호</b></td>
									<td style="width: 35%" align="center"><b>제목</b></td>
									<td style="width: 35%" align="center"><b>내용</b></td>
									<td style="width: 15%" align="center"><b>작성자</b></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="result" items="${NaverEconomy}"
									varStatus="status">
									<tr>
										<td style="width: 15%" align="center" class="listtd"><c:out
												value="${result.idx}" />&nbsp;</td>

										<td style="width: 35%" align="left" class="listtd"><a
											href="${result.href}"><c:out value="${result.title}" />&nbsp;</a></td>

										<td style="width: 35%" align="center" class="listtd"><c:out
												value="${result.contents}" />&nbsp;</td>

										<td style="width: 15%" align="center" class="listtd"><c:out
												value="${result.writer}" />&nbsp;</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
</body>
</html>