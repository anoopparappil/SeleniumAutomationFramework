package com.anoop.selenium.drivermanager;


public class DriverManagerFactory {
	
	public static DriverManager getManager(DriverType driverType) {
		DriverManager driverManager;
		
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
