package com.anoop.selenium.drivermanager;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.WebDriver;

import com.anoop.selenium.utils.NewtorkHandler;

public abstract class DriverManager {
	Logger logger = Logger.getLogger(DriverManager.class);
	protected WebDriver driver;
	protected abstract void createDriver()  throws MalformedURLException,UnknownHostException;
	protected abstract void stopServer();
	
	public void quitDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
	
	public WebDriver getDriver()  throws Exception{
		if(driver == null) {
			startServer();
			createDriver();
		}
		return driver;
	}
	
	protected void startServer() throws Exception{
		logger.info("Starting server");
		GridHubConfiguration config = new GridHubConfiguration();
		config.host = NewtorkHandler.getHubAddress();
		config.port = NewtorkHandler.getHubPort();
		Hub hub = new Hub(config);
		hub.start();
		logger.info("Server started");
		// check http://localhost:4444/grid/api/hub/ slotCounts
		Thread.sleep(10000);
	}
}
