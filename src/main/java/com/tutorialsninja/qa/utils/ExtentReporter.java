package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() 
	{
		// add the extent report library ExtentReports library
		
		 ExtentReports extentreport = new ExtentReports();
		
		 File extentReportFile = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html"); 
		 ExtentSparkReporter sparkreport = new ExtentSparkReporter(extentReportFile);		 
		 sparkreport.config().setTheme(Theme.DARK);
		 sparkreport.config().setReportName("TutorialsNinja Test Automation Result Report");
		 sparkreport.config().setDocumentTitle("TN Automation Report");
		 sparkreport.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");	 
		 extentreport.attachReporter(sparkreport);
		
		 // Get the application URL from config.properties;
		 
		 Properties configprop = new Properties();
		 File configfile = new File(System.getProperty("user.dir") + "//src//main//java//com//tutorialsninja//qa//config//config.properties");
		 try {
			 FileInputStream fisConfigProp = new FileInputStream(configfile);
			 configprop.load(fisConfigProp);
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();		
		}
		 
		 extentreport.setSystemInfo("Application URL", configprop.getProperty("url"));	 
		 // Set property for browser name 
		  extentreport.setSystemInfo("Browser Name", configprop.getProperty("browserName"));
		  extentreport.setSystemInfo("UserName", configprop.getProperty("validUsername"));
		  extentreport.setSystemInfo("Passwords", configprop.getProperty("validPass"));
		  extentreport.setSystemInfo("Operating System", System.getProperty("os.name"));
		  extentreport.setSystemInfo("User Name", System.getProperty("user.name"));
		  extentreport.setSystemInfo("Java Version", System.getProperty("java.version")); 
		  return extentreport;
	}

}
