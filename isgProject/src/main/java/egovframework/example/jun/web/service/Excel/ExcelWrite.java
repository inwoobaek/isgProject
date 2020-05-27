package egovframework.example.jun.web.service.Excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import egovframework.example.jun.web.service.ByungjunVO;
import egovframework.example.jun.web.service.Crawling.ByungjunCrawlingService;

/**
 * @Class Name : ExcelWrite.java
 * @Description : ExcelWrite Class
 * @Modification Information
 * @
 * @  수정일                    수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.05.27     이병준            코멘트 작성
 *
 * @author 인실리코젠 BS팀
 * @since 2020.05.27
 */

/**
 * 5페이지(100개) 최신 게시물을 /isgProject '네이버 뉴스 경제일반.xlsx'생성
 * 
 * @param -
 * @return -
 * @exception -
 * @comment 해당 페이지는 Controller와 연동되어 있지 않으며 발표를 위해 준비한 엑셀쓰기 함수이다.
 */
public class ExcelWrite extends ByungjunCrawlingService {

	private static String[] columns = { "Idx", "Title", "Contents", "Writer", "Page", "Href" };
	private static List<ByungjunVO> contacts = new ArrayList<ByungjunVO>();

	public static void main(String[] args) throws IOException {

		contacts = ByungjunCrawlingService.getExcelCrawling();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("네이버 뉴스 경제일반");
		Font headerFont = workbook.createFont();

		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 17);
		headerFont.setColor(IndexedColors.GREEN.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		int rowNum = 1;

		for (ByungjunVO contact : contacts) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(contact.getIdx());
			row.createCell(1).setCellValue(contact.getTitle());
			row.createCell(2).setCellValue(contact.getContents());
			row.createCell(3).setCellValue(contact.getWriter());
			row.createCell(4).setCellValue(contact.getPage());
			row.createCell(5).setCellValue(contact.getHref());
		}

		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		FileOutputStream fileOut = new FileOutputStream("NaverEconomy.xlsx");
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();

		System.out.println("Excel Success");
	}
}
