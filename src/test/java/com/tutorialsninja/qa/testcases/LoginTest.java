package com.tutorialsninja.qa.testcases;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.text.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utility;
import com.tutorialsninjaqa.pages.AccountPage;
import com.tutorialsninjaqa.pages.HomePage;
import com.tutorialsninjaqa.pages.LogInPage;

public class LoginTest extends Base {

	LogInPage loginpage;

	public LoginTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = initiaseBrowserAndOpenApplicqationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver); // creating the object for Home page means calling the constructor
													// from Homepage.javaclass
		homePage.clickOnMyAccount();
		loginpage = homePage.selectLogInOption();

	}

	@AfterMethod(enabled = true) // After each and every test method browser is clossed
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validSupplytestData")
	public void verifyLoginWithValidCredentials(String email, String pass) {

		AccountPage accountpage = new AccountPage(driver);
		loginpage.enterEmailAdress(email);
		loginpage.enterPassword(pass);
		loginpage.clickOnLogInButton();
		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformation(),
				"Edit your account information option is dsplayed");
	}

	@DataProvider(name = "validSupplytestData")
	public Object[][] supplyTestData() throws IOException {
		/*
		 * // this is data driven and hard coded Object[][] data = {{
		 * "sainathdabhade11@gmail.com", "12345" }, { "sainathdabhade22@gmail.com",
		 * "12345" }, { "sainathdabhade33@gmail.com", "12345" }};
		 */
		Object[][] data = null;
		data = Utility.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {
		AccountPage accountpage = new AccountPage(driver);
		loginpage.enterEmailAdress(Utility.generateEmailWithTimeStamp());
		loginpage.enterPassword(dataprop.getProperty("invalidPasswords"));
		loginpage.clickOnLogInButton();
		String actualMessage = loginpage.retriveInvalidEmailWarningMessage();
		String expectedWarningMessage = dataprop.getProperty("NoMatchEmailWarningMessage");
		Assert.assertTrue(actualMessage.contains(expectedWarningMessage), "Expected message is displayed");
	}

	@Test(priority = 3)
	public void verifyLoginWithInValidemailAndValidPass() {

		loginpage.enterEmailAdress(Utility.generateEmailWithTimeStamp());
		loginpage.enterPassword(dataprop.getProperty("validPasswords"));
		loginpage.clickOnLogInButton();
		String actualMessage = loginpage.retriveInvalidEmailWarningMessage();
		String expectedWarningMessage = dataprop.getProperty("NoMatchEmailWarningMessage");
		Assert.assertTrue(actualMessage.contains(expectedWarningMessage), "Expected message is displayed");
	}

	@Test(priority = 4)
	public void verifyLoginWithValidemailAndInValidPass() throws InterruptedException {

		loginpage.enterEmailAdress(prop.getProperty("validUsername"));
		loginpage.enterPassword(dataprop.getProperty("invalidPasswords"));
		loginpage.clickOnLogInButton();
		String actualMessage = loginpage.retriveInvalidEmailWarningMessage();
		String expectedWarningMessage = dataprop.getProperty("NoMatchEmailWarningMessage");
		Assert.assertTrue(actualMessage.contains(expectedWarningMessage), "Expected message is displayed");
	}

	@Test(priority = 5)
	public void verifyLoginWithoutAnyCredentials() throws InterruptedException {

		loginpage.clickOnLogInButton();
		/*
		 * String actualMessage = driver.findElement(By.
		 * xpath("//div[@class='alert alert-danger alert-dismissible']")) .getText();
		 */
		String actualMessage1 = loginpage.retriveInvalidEmailWarningMessage();
		String expectedWarningMessage = dataprop.getProperty("NoMatchEmailWarningMessage");
		Assert.assertTrue(actualMessage1.contains(expectedWarningMessage), "Expected message is displayed");
	}
}
