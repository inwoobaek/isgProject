package egovframework.example.jun.service;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
 
 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class WriteListToExcelFile {
 
    public static void writeListToFile(String fileName, List<ByungjunVO> noticeList) throws Exception{
        Workbook workbook = null;
         
        if(fileName.endsWith("xlsx")){
            workbook = new XSSFWorkbook();
        }else if(fileName.endsWith("xls")){
            workbook = new HSSFWorkbook();
        }else{
            throw new Exception("invalid file name, should be xls or xlsx");
        }
         
        Sheet sheet = workbook.createSheet("cordova");
         
        Iterator<ByungjunVO> iterator = noticeList.iterator();
         
        int rowIndex = 0;
        int excelname=0; // 처음에는 ID 학번등 고정값을 넣기 위해 사용한 변수
        do{
        	ByungjunVO vo = iterator.next();
            Row row = sheet.createRow(rowIndex++);
           
            
            if(excelname==0){ // 처음에 고정값 
                 
                 Cell cell0 = row.createCell(0);
                 cell0.setCellValue("ID");
                 Cell cell1 = row.createCell(1);
                 cell1.setCellValue("제목");
                 Cell cell2 = row.createCell(2);
                 cell2.setCellValue("내용");
                 Cell cell3 = row.createCell(3);
                 cell3.setCellValue("작성자");
                 Cell cell4 = row.createCell(4);
                 cell4.setCellValue("페이지");
                 excelname++;
                
            }else{  // 다음부터는 순차적으로 값이 들어감 
                 
                 Cell cell0 = row.createCell(0);
                 cell0.setCellValue(vo.getIdx());
                 Cell cell1 = row.createCell(1);
                 cell1.setCellValue(vo.getTitle());
                 Cell cell2 = row.createCell(2);
                 cell2.setCellValue(vo.getContents());
                 Cell cell3 = row.createCell(3);
                 cell3.setCellValue(vo.getWriter());
                 Cell cell4 = row.createCell(4);
                 cell4.setCellValue(vo.getPage());
                
                
            }
            
            
        }while(iterator.hasNext());
     
         
        //lets write the excel data to file now
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        fos.close();
        
        System.out.println(fileName + " written successfully");
    }
}