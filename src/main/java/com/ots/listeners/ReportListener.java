package com.ots.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.ots.dataprovider.ConfigReader;
import com.ots.factory.BrowserFactory;
import com.ots.utility.Utility;

public class ReportListener implements ITestListener 
{
	ExtentReports extent = ExtentManager.getInstance();

	ExtentTest extentTest;

	ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		System.out.println("Starting Test");

		extentTest = extent.createTest(result.getMethod().getMethodName()); //run time we fetch method name and setting our report

		test.set(extentTest);

	}

	public void onTestSuccess(ITestResult result) {
    
		if(ConfigReader.getProperty("screenshotOnSuccess").equalsIgnoreCase("true")) {
			test.get().pass("Test Passed ",MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.captureScreenshotAsBase64(BrowserFactory.getDriver())).build());

		}
		
		else {
			
		test.get().pass("Test Passed ");	
		}
	}

	public void onTestFailure(ITestResult result) 
	{
		
		if(ConfigReader.getProperty("screenshotOnFailure").equalsIgnoreCase("true")) {
		
		//use mediaEntitiyBuilder to get screen shot in a string format. To pass the driver use Browser Factory. To get log use result.getThorwable 
		test.get().fail("Test Failed " +result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.captureScreenshotAsBase64(BrowserFactory.getDriver())).build());

	    }
		else {
			test.get().fail("Test Failed " +result.getThrowable().getMessage());
			
		}
	}
	public void onTestSkipped(ITestResult result) {

		if(ConfigReader.getProperty("screenshotOnSkip").equalsIgnoreCase("true"))
		{
			test.get().skip("Test skipped ", MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.captureScreenshotAsBase64(BrowserFactory.getDriver())).build());
		}
		
		
		else {
			test.get().pass("Test Skipped");
		}

	}

	public void onFinish(ITestContext context) {
		extent.flush(); //add the data to report 
	}

}
