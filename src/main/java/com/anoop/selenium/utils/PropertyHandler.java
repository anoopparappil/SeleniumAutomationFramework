package com.anoop.selenium.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyHandler {
	final static Logger logger = Logger.getLogger(PropertyHandler.class);
	
	public static String getProperty(String property) {
		String filePath = ".//src//main//resources//TestConfg.properties";
		logger.info("Reading property file at '"+filePath+"'");
		File propertyFile = new File(filePath);
		FileInputStream input = null;
		String propertyValue = null;
		try {
			input = new FileInputStream(propertyFile);
			Properties prop = new Properties();
			prop.load(input);
			propertyValue = prop.getProperty(property);
			logger.info("Proprty is "+property+" and value is "+propertyValue);
		}catch(IOException e) {
			logger.error("I/O Operation failed");
			e.printStackTrace();
		}finally {
			try {
				input.close();
			} catch (IOException e) {
				logger.error("Error closing input stream");
				e.printStackTrace();
			}
		}
		return propertyValue;
	}

}
