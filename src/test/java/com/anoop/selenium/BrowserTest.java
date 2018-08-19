package com.anoop.selenium;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.anoop.selenium.drivermanager.DriverManager;
import com.anoop.selenium.drivermanager.DriverManagerFactory;

public class BrowserTest {
	DriverManager driverManager;
	WebDriver driver;
	
	@BeforeTest
	public void config() {
		driverManager = DriverManagerFactory.getManager();
	}
	
	@BeforeMethod
	public void init()  throws MalformedURLException{
		driver = driverManager.getDriver();
	}
	
	@Test
	public void launchBrowser() {
		driver.get("http://google.com");
		Assert.assertEquals(driver.getTitle(), "Google");
	}
	@AfterTest
	public void quit() {
		driverManager.quitDriver();
	}
	

}
