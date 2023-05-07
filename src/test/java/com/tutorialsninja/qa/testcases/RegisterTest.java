package com.tutorialsninja.qa.testcases;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.expectThrows;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utility;
import com.tutorialsninjaqa.pages.AccountSuccessPage;
import com.tutorialsninjaqa.pages.HomePage;
import com.tutorialsninjaqa.pages.RegisterPage;

public class RegisterTest extends Base {
	
	RegisterPage registerpage;

	public RegisterTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebDriver driver;
	@BeforeMethod
	public void setUp() throws IOException

	{	
		driver = initiaseBrowserAndOpenApplicqationURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);	
		homepage.clickOnMyAccount();
	  
		registerpage=	homepage.selectRegisterOption();
	}

	@AfterMethod(enabled = true)
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegistrationAnAccountWithMandetoryField() throws InterruptedException

	{
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		registerpage.enterFirstName(dataprop.getProperty("firstName"));
		registerpage.enterLastName(dataprop.getProperty("lastName"));
		registerpage.enterEmailAddress(Utility.generateEmailWithTimeStamp());
		registerpage.entertelephoneNumber(dataprop.getProperty("telephone"));
		registerpage.enterPasswords(dataprop.getProperty("passwords"));
		registerpage.enterConfirmPasswords(dataprop.getProperty("confirmPasswords"));
		registerpage.clickOnTermsAndConditionCheckbox();
		registerpage.clickOnContinueButton();
		String actualSuccessHeading1 = accountSuccessPage.retriveaccountCreatedSuccessMessage();
		Assert.assertEquals(actualSuccessHeading1, dataprop.getProperty("accountCreatedSuccessMessage"));

	}

	@Test(priority = 2)
	public void verifyRegistrationAnAccountWithAllField() throws InterruptedException

	{		
		AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
		registerpage.enterFirstName(dataprop.getProperty("firstName"));
		registerpage.enterLastName(dataprop.getProperty("lastName"));
		registerpage.enterEmailAddress(Utility.generateEmailWithTimeStamp());
		registerpage.entertelephoneNumber(dataprop.getProperty("telephone"));
		registerpage.enterPasswords(dataprop.getProperty("passwords"));
		registerpage.enterConfirmPasswords(dataprop.getProperty("confirmPasswords"));
		registerpage.clickOnTermsAndConditionCheckbox();
		registerpage.clickOnSubscribeRadioButton();
		registerpage.clickOnContinueButton();
		String actualSuccessHeading = accountsuccesspage.retriveaccountCreatedSuccessMessage();
		Assert.assertEquals(actualSuccessHeading, dataprop.getProperty("accountCreatedSuccessMessage"));
	}

	@Test(priority = 3)
	public void verifyRegistrationAnAccountWithExistingMailAdress() throws InterruptedException

	{
		registerpage.enterFirstName("Sainath");
		registerpage.enterLastName("Dabhade");
		registerpage.enterEmailAddress("sainathdabhade11@gmail.com");
		registerpage.entertelephoneNumber("12345");
		registerpage.enterPasswords("12345");
		registerpage.enterConfirmPasswords("12345");
		registerpage.clickOnSubscribeRadioButton();
		registerpage.clickOnTermsAndConditionCheckbox();
		registerpage.clickOnContinueButton();
		String actualAccountExistMessage = registerpage.retriveAccountAllreadyExistWarningMessage();
		System.out.println(actualAccountExistMessage);
		String ExpectedWarningMessage = dataprop.getProperty("allreadyAccountExsistWarningMessage");
		Assert.assertEquals(actualAccountExistMessage, ExpectedWarningMessage);
	}

	@Test(priority = 4)
	public void verifyRegistrationAnAccountWithoutAnyDetails()
	
	{	
		
		registerpage.clickOnContinueButton();
		String actualWarningPolicyMessage = registerpage.retrivePrivacyPolicyWarningMessage();
		Assert.assertTrue(actualWarningPolicyMessage.contains("Warning: You must agree to the Privacy Policy!"),
				"Privacy policy is not displayed");
		String actualFirstNameWarningMessage = registerpage.retriveFirstNameWarningMessage();
		Assert.assertTrue(actualFirstNameWarningMessage.contains("First Name must be between 1 and 32 characters!"),
				"Not displayed");
		String actualLastNameWarningMessage = registerpage.retriveLastNameWarningMessage();
		Assert.assertTrue(actualLastNameWarningMessage.contains("Last Name must be between 1 and 32 characters!"),
				"Last Name message not displayed");
		String actualEmailWarningMessage = registerpage.retriveEmailWarningMessage();
		Assert.assertTrue(actualEmailWarningMessage.contains("E-Mail Address does not appear to be valid!"),
				"Email is not displayed");
		String actualtelePhoneWarningMessage = registerpage.retrivePhoneNumberWarningMessage();
		Assert.assertTrue(actualtelePhoneWarningMessage.contains("Telephone must be between 3 and 32 characters!"),
				"Telephopne message not displayed");
		String actualtelePasswordsWarningMessage = registerpage.retrivePasswordsWarningMessage();
		Assert.assertTrue(actualtelePasswordsWarningMessage.contains("Password must be between 4 and 20 characters!"),
				"Passwords message is not displayed");
	}

}
