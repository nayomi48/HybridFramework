package com.ots.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ots.dataprovider.ConfigReader;

import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;

public class Utility {
	
	
	public static String getTextandAcceptAlert(WebDriver driver) {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		Alert alt=wait.until(ExpectedConditions.alertIsPresent());
		
		String alert_text=alt.getText();
		
        alt.accept();
        
        return alert_text;
		
	}
	
	
	
	public static void getTextandAcceptAlert(WebDriver driver,String dataTotype) {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		Alert alt=wait.until(ExpectedConditions.alertIsPresent());
		
		alt.sendKeys(dataTotype);
		
        alt.accept();
        
       
		
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static WebElement getElement(WebDriver driver, By locator) {
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
	    
		if(ConfigReader.getProperty("highlightElement").equalsIgnoreCase("true")) {
		
		highlightElement(driver, element);
		          }
		return element;
	}
	
	
	
	
	
//For demo purpose - highlight element code
public static WebElement highlightElement(WebDriver driver, WebElement element) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	 // Change the style of the element to highlight it
    js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; background: yellow;');",element);

    waitForSeconds(1);
    
    // Restore the original style
    js.executeScript("arguments[0].setAttribute('style', 'border: 2px white');", 
                     element);
    return element;
}
	
	//Create first method - click, actions class click, java script click 
	
	public static void clickElement(WebDriver driver, By locator) {
		
		WebElement element=driver.findElement(locator);
		
		Utility.highlightElement(driver, element); //highlight element before click
		
		
		try {
		element.click();
		}
		catch(Exception e) {
			System.out.println("Web element click failed - Trying with actions class click");
			
			try {
			Actions act=new Actions(driver); 
			act.click(driver.findElement(locator)).perform();
		}
			catch(Exception e1) {
				System.out.println("Actions class click failed - trying with java script click");
				
				JavascriptExecutor js= (JavascriptExecutor)driver;
				js.executeScript("arguements[0].click();", driver.findElement(locator));
			}
				
			}
		
	}
	
	
//method overloading
public static void clickElement(WebDriver driver, WebElement element) {
		
		try {
		element.click();
		}
		catch(Exception e) {
			System.out.println("Web element click failed - Trying with actions class click");
			
			try {
			Actions act=new Actions(driver); 
			act.click(element).perform();
		}
			catch(Exception e1) {
				System.out.println("Actions class click failed - trying with java script click");
				
				JavascriptExecutor js= (JavascriptExecutor)driver;
				js.executeScript("arguements[0].click();", element);
			}
				
			}
		
	}
	
	
	
	
	//copied from old Utility file
	public static void waitForSeconds(int seconds) {
		
		try {
			Thread.sleep(1000*seconds);
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted "+e.getMessage());
		}
	}
	
	//copied from old Utility file
	public static String getDateTime() {

	return new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy").format(new Date()); //directly return without saving into a variable

		}
	
	
	//capture screenshots of web element
	public static void captureScreenshot(WebDriver driver, WebElement element) {
			
	File src=element.getScreenshotAs(OutputType.FILE);

	File dest= new File(System.getProperty("user.dir")+"/Screenshots/Element_Screenshot_"+getDateTime()+".png");
				
		try {
				
			FileHandler.copy(src, dest);
			} 
		catch (IOException e) {
				
		System.out.println("Failed to capture Screenshot" +e.getMessage());
			}
			
	}
		

	//capture screenshots of web page
	public static void captureScreenshot(WebDriver driver) {
			
	TakesScreenshot ts= (TakesScreenshot)driver;
			
	File src=ts.getScreenshotAs(OutputType.FILE);

	//Gives current working directory instead of (.)-- and formatted date
	File dest= new File(System.getProperty("user.dir")+"/Screenshots/Screenshot_"+getDateTime()+".png");
				
		try {
				
			FileHandler.copy(src, dest);
			} 
		catch (IOException e) {
				
		System.out.println("Failed to capture Screenshot" +e.getMessage());
			}
			
	}
		
	
	//Capture screenshot as base64 to use with listener 
	
	public static String captureScreenshotAsBase64(WebDriver driver) {
		
		TakesScreenshot ts= (TakesScreenshot)driver;
				
		String src=ts.getScreenshotAs(OutputType.BASE64);
		
		return src;

		}


}
