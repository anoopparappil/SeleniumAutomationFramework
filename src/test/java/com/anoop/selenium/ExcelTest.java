package com.anoop.selenium;

import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.anoop.selenium.datareader.TestDataReader;
import com.anoop.selenium.datareader.impl.ExcelReader;
import com.anoop.selenium.datawriter.TestDataWriter;
import com.anoop.selenium.datawriter.impl.ExcelWriter;

/**
 * Unit test for simple App.
 */
public class ExcelTest {
	Map<Object, Map<Object,Object>> excelData;
	
	@Test (priority = 0)
	public void testExcelReadOperation() {		
		TestDataReader dataReader = new ExcelReader();
		excelData = dataReader.readTestData();
		for(Map.Entry<Object, Map<Object,Object>> entry:excelData.entrySet()) {
			String testCaseId = (String) entry.getKey();
			Map<Object,Object> testDataMap = entry.getValue();
			for(Map.Entry<Object, Object> testDataEntry:testDataMap.entrySet()) {
				String columnHeader = (String) testDataEntry.getKey();
				Object testData = testDataEntry.getValue();
				
				System.out.println("Test Case ID->"+testCaseId+"|Column Name->"+columnHeader+"|Column Value->"+testData);
			}
		}
	}
	
	@Test (priority = 1)
	public void testExcelWriteOperation() throws InterruptedException {
		TestDataWriter dataWriter = new ExcelWriter("TC01_04", "URL", "http://www.selenium.hq", excelData);
		dataWriter.writeTestData();
		Thread thread1 = new Thread((ExcelWriter)dataWriter);
		thread1.start();
//		thread1.join();
		
		dataWriter = new ExcelWriter("TC01_02", "URL", "http://microsoft.com", excelData);
		dataWriter.writeTestData();
		Thread thread2 = new Thread((ExcelWriter)dataWriter);
		thread2 = new Thread((ExcelWriter)dataWriter);
		thread2.start();
//		thread2.join();
		
		dataWriter = new ExcelWriter("TC01_01", "URL", "http://www.gmail.com", excelData);
		dataWriter.writeTestData();
		Thread thread3 = new Thread((ExcelWriter)dataWriter);
		thread3 = new Thread((ExcelWriter)dataWriter);
		thread3.start();
//		thread3.join();
		
		dataWriter = new ExcelWriter("TC01_03", "URL", "http://www.android.com", excelData);
		dataWriter.writeTestData();
		Thread thread4= new Thread((ExcelWriter)dataWriter);
		thread4 = new Thread((ExcelWriter)dataWriter);
		thread4.start();
//		thread4.join();
		System.out.println("-----------------------------Complete-----------------------------");
	}
}
