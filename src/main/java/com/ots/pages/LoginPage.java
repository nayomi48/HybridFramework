package com.ots.pages;
//Page Object model
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ots.utility.Utility;

public class LoginPage {
	
	WebDriver driver;
	
	//Create a constructor (same class name) is needed 
	public LoginPage(WebDriver ldriver) {
	
		driver=ldriver;	//assign this driver to driver we assigned outside
		
	}
	
		
	private By username=By.xpath("//input[@id='email1']");
	
	private By password=By.id("password1");
	
	private By loginbtn=By.xpath("//button[@type='submit']");
	
	private By createNewUser=By.xpath("//a[normalize-space()='New user? Signup']");
	
	//Actions -Method
	
	public HomePage loginToApplication(String uname, String pw) { //parameter username and pw
		
		
		//Highlight the element
		
		//driver.findElement(username).sendKeys(uname);
		
		//Use utility get element
		
		Utility.getElement(driver, username).sendKeys(uname);
		
		/*WebElement userNameelement=driver.findElement(username);
		Utility.highlightElement(driver, userNameelement).sendKeys(uname);*/
	    
		Utility.getElement(driver, password).sendKeys(pw);
		
		/*WebElement passWordelement=driver.findElement(password);
		Utility.highlightElement(driver, passWordelement).sendKeys(pw);*/
		
		//driver.findElement(password).sendKeys(pw);
		
		//driver.findElement(loginbtn).click(); // we can use below utility method for click event 
		
		//Utility.clickElement(driver, loginbtn);
		
		Utility.clickElement(driver, Utility.getElement(driver, loginbtn));
		
		
		HomePage home=new HomePage(driver);
		return home; //return HomePage object once you logged in 
	
	}
	
	/*Web element issues - sync issue
	 * Element is hidden
	 * Scroll
	 * Element is not clickable
	 * Java Script click 
	 */
	
	public Boolean isCreateUserLinkVisible() {
		
		//Boolean link_Status=Utility.highlightElement(driver, driver.findElement(createNewUser)).isDisplayed();
	
		Boolean link_Status= Utility.getElement(driver, createNewUser).isDisplayed();
		return link_Status;
	}


}
