package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class Utility {

	public static final int IMPLICIT_TIME_WAIT = 11;
	public static final int PAGE_LOAD_TIME = 10;

	public static String generateEmailWithTimeStamp() {

		Date date = new Date();

		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");

		return "sainathdabhade11" + timeStamp + "@gmail.com";

	}

	public static Object[][] getTestDataFromExcel(String sheetName) 

	{
		File excelfile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excelfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum(); // get the number of row in the sheet
		int cols = sheet.getRow(0).getLastCellNum(); // get the number of column into the sheet

		Object[][] data = new Object[rows][cols]; // tow dimensional array
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1); // i+1 because we have to switch to second row and read the data

			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
				org.apache.poi.ss.usermodel.CellType cellType = cell.getCellType();

				switch (cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;

				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue()); // here you will get the double
																						// numeric value mean
					// pass = 12345.00 we just want pass=12345 so convert int to string
					break;

				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}

			}

		}

		return data; 
	}
	
	public static String captureScreenshots(WebDriver driver, String testName)
	{
		File srcScreenshots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// above line you will get the driver in object formate
		String destinationScreenShotsPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
		try {
			org.openqa.selenium.io.FileHandler.copy(srcScreenshots, new File(destinationScreenShotsPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationScreenShotsPath;
	}

}
