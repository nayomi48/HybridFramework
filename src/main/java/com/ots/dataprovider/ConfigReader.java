package com.ots.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	//Return type is string 
	
	public static String getProperty (String propertyName){
		
        Properties pro=new Properties(); 
		
		try {
			
			System.out.println("LOG:INFO - Loadig Config FIle");
			pro.load(new FileInputStream(new File("./ConfigurationFiles/Config.properties")));
			
			System.out.println("LOG:INFO - Config File loaded");
		} catch (FileNotFoundException e) {
			
			System.out.println("File not present "+e.getMessage());
		} catch (IOException e) {
			
			System.out.println("COuld not load the file");
		} 
		
        String value =pro.getProperty(propertyName);
		return value;
	  
		
	}
	
	
	//when there are multiple config files 
	
	public static String getProperty (String propertyFileName,String propertyName){
		
        Properties pro=new Properties(); 
		
		try {
			
			System.out.println("LOG:INFO - Loadig Config FIle");
			pro.load(new FileInputStream(new File("./Config/"+propertyFileName+".properties")));
			
			System.out.println("LOG:INFO - Config File loaded");
		} catch (FileNotFoundException e) {
			
			System.out.println("File not present "+e.getMessage());
		} catch (IOException e) {
			
			System.out.println("COuld not load the file");
		} 
		
        String value =pro.getProperty(propertyName);
		return value;
	  
		
	}

}
