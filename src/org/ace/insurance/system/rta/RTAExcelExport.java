package org.ace.insurance.system.rta;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.scheduling.annotation.Scheduled;

public class RTAExcelExport {
	
	private static String date = LocalDate.now().toString();
	@Scheduled(cron="0 14 13 * * ?")
	
//	public static ByteArrayOutputStream createWorkBook(List<ThirdPartyPremiumRecordsDTO> premiumDTOList ) throws IOException {
//
//		String filePath = createFilePath();
//		File file = new File(filePath);
//		FileOutputStream fos = new FileOutputStream(file);
//		ByteArrayOutputStream baStream = new ByteArrayOutputStream();
//		Workbook wb = new HSSFWorkbook();
//		Sheet sh = wb.createSheet("Sheet1");
//		sh.setColumnWidth(1, 4000);
//		sh.setColumnWidth(2, 4000);
//		sh.setColumnWidth(3, 4000);
//		sh.setColumnWidth(4, 6000);
//		sh.setColumnWidth(5, 6000);
//		
//		Row titleRow0 = sh.createRow(0);
//		Cell titleCell0 = titleRow0.createCell(0);
//		titleCell0.setCellValue("Myanma Insurance Premium Buyer Records");
//		
//		Row titleRow1 = sh.createRow(1);
//		Cell titleCell1 = titleRow1.createCell(0);
//		titleCell1.setCellValue("Date : "+date);
//		
//		Row rowHeader = sh.createRow(3);
//		rowHeader(rowHeader,wb);
//		
//		CellStyle rowStyle = wb.createCellStyle();
//		rowStyle.setAlignment(HorizontalAlignment.RIGHT);
//		rowStyle.setBorderBottom(BorderStyle.THIN);
//		rowStyle.setBorderTop(BorderStyle.THIN);
//		rowStyle.setBorderRight(BorderStyle.THIN);
//		rowStyle.setBorderLeft(BorderStyle.THIN);
//		
//		
//		for(int i=0; i < premiumDTOList.size();i++) {
//			Row rw = sh.createRow(i+4);
//			Cell c0 = rw.createCell(0);
//			c0.setCellValue(i+1);
//			c0.setCellStyle(rowStyle);
//			
//			Cell c1 = rw.createCell(1);
//			c1.setCellValue(premiumDTOList.get(i).getVehicle_no());
//			c1.setCellStyle(rowStyle);
//			
//			Cell c2 = rw.createCell(2);
//			c2.setCellValue(premiumDTOList.get(i).getPeriod_from());
//			c2.setCellStyle(rowStyle);
//			
//			Cell c3 = rw.createCell(3);
//			c3.setCellValue(premiumDTOList.get(i).getPeriod_to());
//			c3.setCellStyle(rowStyle);
//
//			Cell c4 = rw.createCell(4);
//			c4.setCellValue(premiumDTOList.get(i).getRta_branch());
//			c4.setCellStyle(rowStyle);
//			
//			Cell c5 = rw.createCell(5);
//			c5.setCellValue(premiumDTOList.get(i).getAddress());
//			c5.setCellStyle(rowStyle);
//		}
//		wb.write(fos);
//		wb.write(baStream);
//		wb.close();
//		return baStream;
//	}

	private static String createFilePath() {
		boolean os = System.getProperty("os.name").startsWith("Window");
		String path="";
		if(os) {
			String directory = "D:\\ExcelDailyExport";
			 File dir = new File(directory);
		        if (!dir.exists()) {
		        	dir.mkdirs();
		        }
		        path = Paths.get(directory + File.separatorChar +"MIP-records-"+ date+".xls").toUri().getPath();
		}
		return path;
	}


	private static void rowHeader(Row rowHeader,Workbook wb) {
		Cell c1 = rowHeader.createCell(0);
		c1.setCellValue("No");
		Cell c2 = rowHeader.createCell(1);
		c2.setCellValue("Car No");
		Cell c3 = rowHeader.createCell(2);
		c3.setCellValue("From Date");
		Cell c4 = rowHeader.createCell(3);
		c4.setCellValue("To Date");
		Cell c5 = rowHeader.createCell(4);
		c5.setCellValue("Branch");
		Cell c6 = rowHeader.createCell(5);
		c6.setCellValue("Address");
		CellStyle headerStyle = wb.createCellStyle();
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		c1.setCellStyle(headerStyle);
		c2.setCellStyle(headerStyle);
		c3.setCellStyle(headerStyle);
		c4.setCellStyle(headerStyle);
		c5.setCellStyle(headerStyle);
		c6.setCellStyle(headerStyle);
	}
}
