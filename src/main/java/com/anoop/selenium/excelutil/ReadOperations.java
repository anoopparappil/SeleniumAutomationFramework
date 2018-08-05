package com.anoop.selenium.excelutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.anoop.selenium.utils.PropertyHandler;

public class ReadOperations {
	final static Logger logger = Logger.getLogger(ReadOperations.class);
	Workbook workBook;
	Sheet sheet;
	public Map<Object, Map<Object,Object>> getSheetData(){
		String filePath = PropertyHandler.getProperty("test.data.file");
		logger.info("Excel data file path is '"+filePath+"'");
		String fileExtension = filePath.substring(filePath.lastIndexOf('.'),filePath.length());
		File excelFile = new File(filePath);
		FileInputStream input = null;
		String testCaseColumnHeader;
		Map<Object,Map<Object,Object>> sheetDataMap = new HashMap<Object, Map<Object,Object>>();
		try {
			input = new FileInputStream(excelFile);
			if(fileExtension.equals("xls")) {
				workBook = new HSSFWorkbook(input);
			}else {
				workBook = new XSSFWorkbook(input);
			}
			for(int sheetNumber = 0; sheetNumber<workBook.getNumberOfSheets(); sheetNumber++) {
				sheet = workBook.getSheetAt(sheetNumber);
				logger.info("Reading sheet -"+sheet.getSheetName());
				int numberOfRows = sheet.getPhysicalNumberOfRows();
				int numberOfCols = sheet.getRow(0).getPhysicalNumberOfCells();
				List<String> columnHeaders = getColumnHeaders(sheet);
				testCaseColumnHeader = PropertyHandler.getProperty("test.case.column.name");
				int indexOfTestCaseId = columnHeaders.indexOf(testCaseColumnHeader);
				logger.info("Test case id at position "+indexOfTestCaseId);
				for(int i=1; i<numberOfRows; i++) {
					logger.info("Reading row "+i);
					Map<Object, Object> columValueMap = new HashMap<Object, Object>();
					String testCaseId = null;
					for(int j=0; j<numberOfCols; j++) {
						if(j == indexOfTestCaseId) {
							testCaseId = (String) getCellData(sheet, i, j);
						}else {
							String columnHeader = columnHeaders.get(j);
							Object columnValue = getCellData(sheet, i, j);
							columValueMap.put(columnHeader, columnValue);
						}
					}
					sheetDataMap.put(testCaseId, columValueMap);
					
				}
			}
		} catch (FileNotFoundException e) {
			logger.error("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("I/O operation failed");
			e.printStackTrace();
		}finally {
			try {
				input.close();
			} catch (IOException e) {
				logger.error("Error closing input stream");
				e.printStackTrace();
			}
		}
		return sheetDataMap;
	}
	
	public List<String> getColumnHeaders(Sheet sheet){
		List<String> headerNames = new ArrayList<String>();
		int numberOfCols = sheet.getRow(0).getPhysicalNumberOfCells();
		for(int i=0; i<numberOfCols; i++) {
			String columnData = (String) getCellData(sheet, 0, i);
			logger.info("Column header name is - "+columnData);
			headerNames.add(columnData);
		}
		return headerNames;
	}
	
	public Object getCellData(Sheet sheet, int rowIndex, int columnIndex) {
		Cell cell;
		Row row;
		row = sheet.getRow(rowIndex);
		cell = row.getCell(columnIndex);
		Object data;
		if(cell.getCellTypeEnum() == CellType.BLANK) {
			data = "";
		}else if(cell.getCellTypeEnum() == CellType.BOOLEAN) {
			data = cell.getBooleanCellValue();
		}else if(cell.getCellTypeEnum() == CellType.ERROR) {
			data = PropertyHandler.getProperty("error.cell.return.value");
		}else if(cell.getCellTypeEnum() == CellType.FORMULA) {
			switch(cell.getCachedFormulaResultTypeEnum()) {
				case BLANK:
					data = "";
					break;
				case BOOLEAN:
					data = cell.getBooleanCellValue();
					break;
				case ERROR:
					data = PropertyHandler.getProperty("error.cell.return.value");
					break;
				case NUMERIC:
					data = cell.getNumericCellValue();
					break;
				case STRING:
					data = cell.getStringCellValue();
					break;
				default:
					data = null;
			}
			
		}else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
			data = cell.getNumericCellValue();
		}else if(cell.getCellTypeEnum() == CellType.STRING) {
			data = cell.getStringCellValue();
		}else {
			data = null;
		}
		logger.info("Cell data is - "+data);
		return data;
	}

}
