package com.ots.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ots.dataprovider.ConfigReader;
import com.ots.factory.BrowserFactory;

//Use browser Factory to deal with different browsers and timeouts 
public class Base {
	
	
public WebDriver driver;
	
	
	@BeforeMethod
	public void setUp() {
		
		
		System.out.println("LOG:INFO - Starting browser-Running Before Method");
		
		driver=BrowserFactory.startBrowser(ConfigReader.getProperty("browserName"), ConfigReader.getProperty("appURLQA")+"/login");
		
		System.out.println("LOG:INFO - Browser and Application up and running");
		
		
		
	}
	
	@AfterMethod
	public void tearDown() {
	System.out.println("LOG:INFO - Closing browser-Running After Method");
   
	BrowserFactory.closeBrowser(driver);
    
	System.out.println("LOG:INFO - Session Closed");
	}

}
