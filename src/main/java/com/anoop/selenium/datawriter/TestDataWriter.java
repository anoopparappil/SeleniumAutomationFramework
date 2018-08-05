package com.anoop.selenium.datawriter;



public interface TestDataWriter {

	/**
	 * This method update data in the data source.
	 * Updated data will be available in both test data map and data source
	 * @param testCaseID
	 * @param columnName
	 * @param value
	 * @param testData
	 */
	public void writeTestData();
}
