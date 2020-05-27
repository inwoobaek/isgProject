<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>이수호 프로젝트</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=2">
<link rel="stylesheet"
	href="<c:url value='/css/bootstrap/css/suhobootstrap.min.css'/>" />
<script src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<script src="<c:url value='/css/bootstrap/js/bootstrap.min.js'/>"></script>

<script type="text/javaScript" language="javascript" defer="defer">
	function excelDown(id, title) {
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
		var fileName = title + '.xls';

		// 엑셀 다운로드 IE
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
	
	function list() {
		location.href = "<c:url value='/suhoList.do'/>"
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

			<div class="panel-heading" style="background-color: #006ca5">
			<button type="button"  class="btn btn-danger"
					onclick="list();">홈으로</button>
				<button type="button"  class="btn btn-warning"
					onclick="excelDown('table', 'NewsCrawler');">엑셀
						다운로드(최근 100개 기사)</button>
			</div>

			<div class="panel-body">
				<div class="table-responsive">
					<table id="table" class="table table-hover">
						<thead>
							<tr>
								<th class="text-center" style="width: 5%">번호</th>
								<th class="text-center" style="width: 35%">제목</th>
								<th class="text-center" style="width: 40%">내용</th>
								<th class="text-center" style="width: 10%">등록자</th>
								<th class="text-center" style="width: 10%">등록시간</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${excelList}" varStatus="status">
								<tr>
									<td align="center" class="listtd"><c:out
											value="${result.idx}" />&nbsp;</td>
									<td align="center" class="listtd"><a href="${result.href}"
										target="_blank"><c:out value="${result.title}" />&nbsp;</a></td>
									<td align="center" class="listtd"><c:out
											value="${result.view}" />&nbsp;</td>
									<td align="center" class="listtd"><c:out
											value="${result.writer}" />&nbsp;</td>

									<c:choose>
										<c:when test="${result.newdate != null}">
											<td align="center" class="listtd"><c:out
													value="${result.newdate}" />&nbsp;</td>
										</c:when>
										<c:when test="${result.outdate != null}">
											<td align="center" class="listtd"><c:out
													value="${result.outdate}" />&nbsp;</td>
										</c:when>
									</c:choose>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
</body>
</html>