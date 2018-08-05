package com.anoop.selenium.datareader;

import java.util.Map;

public interface TestDataReader {
	
	/**
	 * This method read data from data source and return as Map.
	 * Key of the inner map is the column name of the data set and value is the actual data.
	 * Key of the outer map is the test case id.
	 * @return
	 */
	public Map<Object, Map<Object,Object>> readTestData();
}
