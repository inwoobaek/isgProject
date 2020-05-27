package egovframework.example.suho.service;

import java.io.*;
import java.util.*;



import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelTest {

	private int idx;
	private String title;
	private String writer;
	private String view;
	private String href;
	
	
	public static void ExcelWrite(List<SuhoVO> list) {
		// 워크북 생성
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 워크시트 생성
		XSSFSheet sheet = workbook.createSheet();
		
		XSSFCreationHelper createHelper = workbook.getCreationHelper();
		
		// 행 생성
		
		sheet.setColumnWidth(1, 10000);
		sheet.setColumnWidth(2, 10000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 20000);
		
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFCellStyle style2 = workbook.createCellStyle();
		
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		
		
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(headerFont);
		
		style2.setAlignment(HorizontalAlignment.CENTER);
		style2.setVerticalAlignment(VerticalAlignment.CENTER);
		style2.setBorderTop(BorderStyle.THIN);
		style2.setBorderLeft(BorderStyle.THIN);
		style2.setBorderRight(BorderStyle.THIN);
		style2.setBorderBottom(BorderStyle.THIN);
		style2.setWrapText(true);
		
		
		XSSFRow row = sheet.createRow(0);
		// 셀 생성
		XSSFCell cell;
		
		cell = row.createCell(0);
		cell.setCellValue("번호");
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue("제목");
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue("내용");
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue("등록자");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("링크");
		cell.setCellStyle(style);
		
		for(SuhoVO s : list) {
			row = sheet.createRow(s.getIdx());
			
			cell = row.createCell(0);
			cell.setCellValue(s.getIdx());
			cell.setCellStyle(style2);
			
			cell = row.createCell(1);
			cell.setCellValue(s.getTitle());
			cell.setCellStyle(style2);
			
			cell = row.createCell(2);
			cell.setCellValue(s.getView());
			cell.setCellStyle(style2);
			
			cell = row.createCell(3);
			cell.setCellValue(s.getWriter());
			cell.setCellStyle(style2);
			
			cell = row.createCell(4);
			cell.setCellValue(s.getHref());
			cell.setCellStyle(style2);
			
		}
		
		try {
		FileOutputStream fos = new FileOutputStream("C:\\Temp\\testWrite.xlsx");
		workbook.write(fos);
		fos.close();
		
		System.out.println("파일 생성 완료");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
