package com.anoop.selenium.drivermanager;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager{

	@Override
	protected void createDriver() {
		driver = new FirefoxDriver();
		
	}

	@Override
	protected void stopServer() {
		// TODO Auto-generated method stub
		
	}

}
