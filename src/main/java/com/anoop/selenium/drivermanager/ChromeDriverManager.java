package com.anoop.selenium.drivermanager;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.anoop.selenium.utils.NewtorkHandler;
import com.anoop.selenium.utils.PropertyHandler;

public class ChromeDriverManager extends DriverManager{

	@Override
	protected void createDriver() throws MalformedURLException, UnknownHostException {
		String ipAddress = NewtorkHandler.getHubRegistrationAddress();
		String driverPath = PropertyHandler.getProperty("chrome.driver.path");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.binary", driverPath);
		driver = new RemoteWebDriver (new URL(ipAddress),capabilities);
		
	}

	@Override
	protected void stopServer() {
		// TODO Auto-generated method stub
		
	}

	
}
