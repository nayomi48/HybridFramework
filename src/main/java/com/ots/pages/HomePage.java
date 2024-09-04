package com.ots.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class HomePage {
	
	//Step 01- we need driver
	WebDriver driver;
	
	//step 02-we need constructor
	public HomePage(WebDriver driver) {
	this.driver=driver;
		
	}
	
	//locators
	private By welcomemsg= By.xpath("//h4[@class='welcomeMessage']");
	
	private By cartIcon=By.xpath("//button[@class='cartBtn']");// To access cart page
	
	private By manageOption=By.xpath("//span[normalize-space()='Manage']"); //To access manage courses (mouse hover)page
	
	private By manageCourses=By.xpath("//a[normalize-space()='Manage Courses']");
	
	//methods - action
	public boolean isWelcomeMessageDisplayed() {
		
	boolean status=driver.findElement(welcomemsg).isDisplayed();
	return status ;
		
	}


}
