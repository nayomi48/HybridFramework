package com.ots.factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.ots.dataprovider.ConfigReader;

//Same as Utility - Here only deal with browsers 

public class BrowserFactory {
	
	static WebDriver driver;
		
	
//Getter method to use with ReportListner class
	
	public static WebDriver getDriver(){
		
		return driver;
	}
	
	
public static WebDriver startBrowser(String browser, String appURL) {
	
	
	  System.out.println("LOG:INFO - Starting "+appURL+ "on "+browser);
	  
	  
	  if(browser.equalsIgnoreCase("chrome")) {
		  
	  //make headless
		  
	  ChromeOptions opt=new ChromeOptions();
	  
	  if (ConfigReader.getProperty("headless").equalsIgnoreCase("true")) {
		  
	  System.out.println("LOG:INFO - Test is running on Chrome headless mode");
	  opt.addArguments("--headless=new");
	  }
	  driver=new ChromeDriver(opt);
	  }
	  
		if(browser.equalsIgnoreCase("firefox")) {
			
		//make headless	
			
		FirefoxOptions opt= new FirefoxOptions();
		
		  if (ConfigReader.getProperty("headless").equalsIgnoreCase("true")) {
			  
			  System.out.println("LOG:INFO - Test is running on FF headless mode");
			  opt.addArguments("--headless");
			  }
		
		driver=new FirefoxDriver(opt);	
		}
		
		if(browser.equalsIgnoreCase("edge")) {
			
		//make headless using options
			
		EdgeOptions opt= new EdgeOptions();
		
		 if (ConfigReader.getProperty("headless").equalsIgnoreCase("true")) {
			  
			  System.out.println("LOG:INFO - Test is running on Edge headless mode");
			  opt.addArguments("--headless=new");
			  }
		
		
		driver=new EdgeDriver(opt);
		}
		
		//Maintain wait
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("pageLoadWait")))); //convert String to Long we use parse 
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("scriptWait"))));
		driver.manage().window().maximize();
		
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait"))));
		

		return driver;
		
		
	}

public static void closeBrowser(WebDriver driver) { // to close only one browser 
	driver.quit();
}
		
}
