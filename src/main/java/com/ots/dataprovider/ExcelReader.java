package com.ots.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

//parameterized excel reader to get data from any excel - will be using this in framework

public class ExcelReader {

static XSSFWorkbook wb;

  public static Object[][] getDataFromExcel(String sheetName){  //static method you can access ExcelReader.method name
	  
	   try {
		wb= new XSSFWorkbook(new FileInputStream(new File("./TestData/TestData.xlsx")));
		
		System.out.println("LOG: INFO - Loading Excel WorkBook");
	} catch (IOException e) {
		System.out.println("Could not load the data file "+e.getMessage());
	}
		
		int row=wb.getSheet(sheetName).getPhysicalNumberOfRows(); //pass the sheet name, get number of columns 
		
		int col= wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells(); //pass the sheet name,Get number of columns 
		
		Object[][]arr=new Object[row-1][col]; //pass rows and columns - to create 2 D array
			
		for(int i=1;i<row;i++) {
			 for (int j=0;j<col;j++) {
				 
				  arr[i-1][j]=getData(sheetName,i,j);
			  }
				
		}
	
		System.out.println("LOG: INFO -  Excel WorkBook completed");
		
		try {
			wb.close();
		} catch (IOException e) {
		System.out.println("Could not close the data file "+e.getMessage());
		}  
	  
  return arr; //return object array 
  
    }
  
//To get any type of data 
	public static String getData(String sheetName, int row, int column) {
	
	String value="";
		
	CellType type =wb.getSheet(sheetName).getRow(row).getCell(column).getCellType(); //returns type of cell 
	
	if(type==CellType.NUMERIC) {
		 
		//This will return a double value. Convert numeric to a string value 
		double dvalue=wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue(); //return a double value
		value= String.valueOf(dvalue); //double value convert to a string
	}
	else if(type==CellType.BOOLEAN) {
		//This will return a boolean value. Convert boolean to a string value 
		boolean bvalue=wb.getSheet(sheetName).getRow(row).getCell(column).getBooleanCellValue(); 
		value= String.valueOf(bvalue);
	}
	else if(type==CellType.STRING) {
		value=wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}
	
	else if(type==CellType.BLANK) {
		value=""; //return blank String
		
	}
	return value; //return String
	}
}