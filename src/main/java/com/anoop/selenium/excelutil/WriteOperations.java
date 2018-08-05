package com.anoop.selenium.excelutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.anoop.selenium.utils.PropertyHandler;

public class WriteOperations {
	final static Logger logger = Logger.getLogger(WriteOperations.class);
	Workbook workBook;
	Sheet sheet;
	public void setColumnData(String testCase,String columnName,Object data) {
		synchronized (this) {
			ReadOperations readOperations = new ReadOperations();
			String filePath = PropertyHandler.getProperty("test.data.file");
			logger.info("Excel file path is '"+filePath+"'");
			String fileExtension = filePath.substring(filePath.lastIndexOf('.'),filePath.length());
			File excelFile = new File(filePath);
			FileOutputStream output = null;
			FileInputStream input = null;
			String testCaseColumnHeader;
			try {
				input = new FileInputStream(excelFile);
				if(fileExtension.equals("xls")) {
					workBook = new HSSFWorkbook(input);
				}else {
					workBook = new XSSFWorkbook(input);
				}
				
				for(int sheetNumber = 0; sheetNumber<workBook.getNumberOfSheets(); sheetNumber++) {
					sheet = workBook.getSheetAt(sheetNumber);
					logger.info("Checking sheet "+sheet.getSheetName());
					int numberOfRows = sheet.getPhysicalNumberOfRows();
					List<String> columnHeaders = readOperations.getColumnHeaders(sheet);
					testCaseColumnHeader = PropertyHandler.getProperty("test.case.column.name");
					int indexOfExcelColumn = columnHeaders.indexOf(columnName);
					int indexOfTestCaseColumn = columnHeaders.indexOf(testCaseColumnHeader);
					int testDataRow = 0;
					for(int i=1; i<numberOfRows; i++) {
						String cellData = (String) readOperations.getCellData(sheet, i, indexOfTestCaseColumn);
						if(cellData.equals(testCase)) {
							testDataRow = i;
							logger.info("Test Case ID found");
							break;
						}
					}
					if(testDataRow != 0) {
						setCellData(sheet, testDataRow, indexOfExcelColumn, data);
						input.close();
						output = new FileOutputStream(excelFile,true);
						workBook.write(output);
						break;
					}
					logger.info("Test Case ID not foud, searching next sheet");
				}
			}catch(FileNotFoundException e) {
				logger.error("File not found");
				e.printStackTrace();
			}catch(IOException e) {
				logger.error("I/O operation failed");
				e.printStackTrace();
			}finally {
				try {
					output.close();
				} catch (IOException e) {
					logger.error("Error on closing output stream");
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setCellData(Sheet sheet, int rowIndex, int columnIndex,Object data) {
		Cell cell;
		Row row;
		logger.info("Setting cell data");
		row = sheet.getRow(rowIndex);
		cell = row.getCell(columnIndex);
		if(data instanceof RichTextString)
			cell.setCellValue((RichTextString) data);
		if(data instanceof Integer)
			cell.setCellValue((Integer) data);
		if(data instanceof Boolean)
			cell.setCellValue((Boolean) data);
		if(data instanceof Date)
			cell.setCellValue((Date) data);
		if(data instanceof Double)
			cell.setCellValue((Double) data);
		if(data instanceof Calendar)
			cell.setCellValue((Calendar) data);
		else			
			cell.setCellValue((String) data);
		logger.info("Cell data successfully set");
	}
}
