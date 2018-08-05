package com.anoop.selenium.datawriter.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.anoop.selenium.datawriter.TestDataWriter;
import com.anoop.selenium.excelutil.WriteOperations;
import com.anoop.selenium.utils.TestDataMap;

public class ExcelWriter implements TestDataWriter, Runnable{
	final static Logger logger = Logger.getLogger(ExcelWriter.class);
	String testCaseID;
	String columnName;
	String value;
	Map<Object, Map<Object, Object>> testData;
	
	public ExcelWriter(String testCaseID, String columnName, String value,Map<Object, Map<Object, Object>> testData){
		this.testCaseID = testCaseID;
		this.columnName = columnName;
		this.value = value;
		this.testData = testData;
	}
	public void writeTestData() {		
		TestDataMap.updateTestDataMap(testData, testCaseID, columnName, value);		
	}

	public void run() {
		logger.info("New thread '"+Thread.currentThread().getName()+"' started to write data to excel");
		WriteOperations writeOperations = new WriteOperations();
		writeOperations.setColumnData(testCaseID, columnName, value);
		logger.info("Thread '"+Thread.currentThread().getName()+"' completed excel write operation");
	}

}
