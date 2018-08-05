package com.anoop.selenium.datareader.impl;

import java.util.Map;

import com.anoop.selenium.datareader.TestDataReader;
import com.anoop.selenium.excelutil.ReadOperations;

public class ExcelReader implements TestDataReader{

	public Map<Object, Map<Object, Object>> readTestData() {
		ReadOperations excelReadOperations = new ReadOperations();
		return excelReadOperations.getSheetData();
	}

}
