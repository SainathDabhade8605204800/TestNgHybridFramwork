package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

import javax.swing.text.Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.tutorialsninja.qa.utils.Utility;

public class MyListeners implements ITestListener {
	private static final OutputType<File> OutputType = null;
	ExtentReports extentReport;
	ExtentTest extetTest;
	String testName;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		testName = result.getName();
		extetTest = extentReport.createTest(testName);
		extetTest.log(Status.INFO, testName + "Started Execution");
		System.out.println(testName + "Started Execution");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extetTest.log(Status.PASS, testName + "Got successfully executed");
		System.out.println(testName + " Got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String destinationScreenShotsPath = Utility.captureScreenshots(driver, testName);
		extetTest.addScreenCaptureFromPath(destinationScreenShotsPath);
		extetTest.log(Status.INFO, result.getThrowable()); // print why test case get failed
		extetTest.log(Status.FAIL, testName + "Got failed");

		System.out.println(testName + " Got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extetTest.log(Status.INFO, result.getThrowable());
		extetTest.log(Status.SKIP, testName + " Got Skipped");

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		extentReport = com.tutorialsninja.qa.utils.ExtentReporter.generateExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentReport.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// select MyListener -> CLick on Sourrce -> Select Override/implemented method
	// option

}
