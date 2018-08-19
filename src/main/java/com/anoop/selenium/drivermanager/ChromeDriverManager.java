package com.anoop.selenium.drivermanager;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.anoop.selenium.utils.PropertyHandler;

public class ChromeDriverManager extends DriverManager{

	@Override
	protected void createDriver() throws MalformedURLException {
		
		String driverPath = PropertyHandler.getProperty("chrome.driver.path");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.binary", driverPath);
		driver = new RemoteWebDriver (new URL("http://localhost:4444/wd/hub"),capabilities);
		
	}

	
}
