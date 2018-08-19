package com.anoop.selenium.drivermanager;

import com.anoop.selenium.utils.PropertyHandler;

public class DriverManagerFactory {
	
	public static DriverManager getManager() {
		DriverManager driverManager;
		String browser = PropertyHandler.getProperty("browser");
		DriverType driverType = DriverType.valueOf(browser.toUpperCase());
		
		switch(driverType) {
		case CHROME:
			driverManager = new ChromeDriverManager();
			break;
		case FIREFOX:
			driverManager = new FirefoxDriverManager();
			break;
		default:
			driverManager = null;
		}
		return driverManager;
	}

}
