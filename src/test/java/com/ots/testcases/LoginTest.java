package com.ots.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ots.base.Base;
import com.ots.dataprovider.CustomDataProviders;
import com.ots.pages.HomePage;
import com.ots.pages.LoginPage;

public class LoginTest extends Base{
	LoginPage login;	//to access by other methods keep outside

	
	
	@Test(dataProvider ="LoginTestData",dataProviderClass = CustomDataProviders.class) //give data provider name and class 
	public void validLogin(String userName, String passWord) {
		
		login = new LoginPage(driver);
		
		HomePage home=login.loginToApplication(userName, passWord); //loginTpApplication returns HomePage
		
		Assert.assertTrue(home.isWelcomeMessageDisplayed(), "Login Failed");
	}
	
	
	@Test
	public void verifyNewUserLink() {
		
		login = new LoginPage(driver); //Session issue - so created this again
		
		Assert.assertTrue(login.isCreateUserLinkVisible(), "New User Creation link is not present");
	}

}
