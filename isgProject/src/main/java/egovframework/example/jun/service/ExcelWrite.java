package egovframework.example.jun.service;

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

public class ExcelWrite extends ByungjunCrawlingService{
	
	private static String[] columns = {"Idx", "Title", "Contents", "Writer", "Page", "Href"};
	private static List<ByungjunVO> contacts = new ArrayList<ByungjunVO>();
	
	public static void main(String[] args) throws IOException {

		contacts = ByungjunCrawlingService.getExcelCrawling();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("네이버 뉴스 경제일반");
		Font headerFont = workbook.createFont();
		
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short)17);
		headerFont.setColor(IndexedColors.GREEN.getIndex());
		
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		
		Row headerRow = sheet.createRow(0);
		
		for( int i=0; i<columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}
		
		int rowNum = 1;
		
		for(ByungjunVO contact : contacts) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(contact.getIdx());
			row.createCell(1).setCellValue(contact.getTitle());
			row.createCell(2).setCellValue(contact.getContents());
			row.createCell(3).setCellValue(contact.getWriter());
			row.createCell(4).setCellValue(contact.getPage());
			row.createCell(5).setCellValue(contact.getHref());
		}
		
		for( int i=0; i<columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		
		FileOutputStream fileOut = new FileOutputStream("NaverEconomy.xlsx");
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
		
		System.out.println("Excel Success");
	}
}
