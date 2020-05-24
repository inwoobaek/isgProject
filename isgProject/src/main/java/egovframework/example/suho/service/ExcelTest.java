package egovframework.example.suho.service;

import java.io.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelTest {

	private int idx;
	private String title;
	private String writer;
	private String view;
	private String href;
	
	
	public static void ExcelWrite() {
		// 워크북 생성
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 워크시트 생성
		XSSFSheet sheet = workbook.createSheet();
		// 행 생성
		XSSFRow row = sheet.createRow(0);
		// 셀 생성
		XSSFCell cell;
		
		cell = row.createCell(0);
		cell.setCellValue("순번");
		
		cell = row.createCell(1);
		cell.setCellValue("제목");

		cell = row.createCell(2);
		cell.setCellValue("작성자");
		
		cell = row.createCell(3);
		cell.setCellValue("내용");
		
		cell = row.createCell(4);
		cell.setCellValue("링크");
		
		try {
		FileOutputStream fos = new FileOutputStream("C:\\testWrite.xlsx");
		workbook.write(fos);
		fos.close();
		
		System.out.println("파일 생성 완료");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
