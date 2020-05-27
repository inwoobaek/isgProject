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

	private int idx; // 번호
	private String title; // 제목
	private String writer; // 등록자
	private String view; // 내용
	private String href; // 링크
	
	/*poi를 이용하여 엑셀 파일을 쓰는 클래스입니다.*/
	public static void ExcelWrite(List<SuhoVO> list) {
		// 워크북 생성
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 워크시트 생성
		XSSFSheet sheet = workbook.createSheet();
	

		// 열 높이 조절
		sheet.setColumnWidth(1, 10000);
		sheet.setColumnWidth(2, 10000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 20000);
		
		// 헤더 부분에 스타일을 주기 위한 인스턴스
		XSSFCellStyle style = workbook.createCellStyle();
		
		// 바디 부분에 스타일을 주기 위한 인스턴스
		XSSFCellStyle style2 = workbook.createCellStyle();
		
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		
		// 가로와 세로를 모두 가운데 정렬
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		
		// 상하좌우에 모두 테두리 설정
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(headerFont);
		
		// 가로와 세로를 모두 가운데 정렬
		style2.setAlignment(HorizontalAlignment.CENTER);
		style2.setVerticalAlignment(VerticalAlignment.CENTER);
		
		// 상하좌우에 모두 테두리 설정
		style2.setBorderTop(BorderStyle.THIN);
		style2.setBorderLeft(BorderStyle.THIN);
		style2.setBorderRight(BorderStyle.THIN);
		style2.setBorderBottom(BorderStyle.THIN);
		style2.setWrapText(true);
		
		// 행 생성
		XSSFRow row = sheet.createRow(0);
		
		// 셀 생성
		XSSFCell cell;
		
		// 헤더 내용 표시
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
		
		//바디 부분에 들어갈 데이터인 SuhoVO List
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
		FileOutputStream fos = new FileOutputStream("./testExcel.xlsx");
		workbook.write(fos);
		fos.close();
		
		System.out.println("파일 생성 완료");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
