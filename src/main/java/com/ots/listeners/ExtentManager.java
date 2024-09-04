package com.ots.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ots.utility.Utility;

public class ExtentManager {

	static ExtentReports extent;
	
	public static ExtentReports getInstance()
	{
		if(extent==null) // if report is not created create a report
		{
			extent=createInstance();
			
			return extent;
		}
		else
		{
			return extent;
		}
	}
	
	public static ExtentReports createInstance()
	{
		//To stop overriding the same report we use date time 
		
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter("./Reports/Automation_Report_"+Utility.getDateTime()+".html");
		
		sparkReporter.config().setTheme(Theme.DARK);
		
		sparkReporter.config().setReportName("Sprint 1 Report");
		
		sparkReporter.config().setDocumentTitle("Automation Report");
		
		ExtentReports extent=new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		
		return extent;
	}

}