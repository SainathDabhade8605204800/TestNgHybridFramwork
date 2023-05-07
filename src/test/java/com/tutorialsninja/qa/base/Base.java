package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utility;

public class Base {
	public WebDriver driver;
	public Properties prop;
	public Properties dataprop;

	public Base() throws IOException

	{
		// load the data from config properties file
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);

		// load the data from testdata propertiees file
		dataprop = new Properties();

		File propFile1 = new File(System
				.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		
		FileInputStream fis1 = new FileInputStream(propFile1);
		dataprop.load(fis1);
	}

	public WebDriver initiaseBrowserAndOpenApplicqationURL(String browserName)

	{

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\sdabhade\\Downloads\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Utility.IMPLICIT_TIME_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Utility.PAGE_LOAD_TIME, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
