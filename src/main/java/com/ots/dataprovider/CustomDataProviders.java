package com.ots.dataprovider;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;



public class CustomDataProviders {
	
	@DataProvider(name="LoginTestData")

	public static Object[][] generateTestDataFromExcel() { 
		
		Reporter.log("Preparing Test Data from Excel",true);
		
		Object[][] arr=ExcelReader.getDataFromExcel("LoginCredentials"); //pass the sheet name here 
		
		Reporter.log("Test data prepared ",true);
		
		return arr;
		
	}
}
