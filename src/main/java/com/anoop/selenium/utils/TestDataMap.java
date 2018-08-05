package com.anoop.selenium.utils;

import java.util.Map;

public class TestDataMap {

	public static void updateTestDataMap(Map<Object, Map<Object,Object>> testDataMap,String testCase,String columnName,Object data) {
		Map<Object,Object> testData = testDataMap.get(testCase);
		testData.put(columnName, data);
		return;
	}
}
