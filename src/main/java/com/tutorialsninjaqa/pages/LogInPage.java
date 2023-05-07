package com.tutorialsninjaqa.pages;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

	WebDriver driver;

	@FindBy(xpath = "//input[@name='email']")
	WebElement emailAddressField;

	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordsField;

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	WebElement logInButton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement inValidEmailWarningMessage; 
	

	public LogInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	// Action Method

	public void enterEmailAdress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}

	public void enterPassword(String password) {
		passwordsField.sendKeys(password);
	}

	public void clickOnLogInButton() {
		logInButton.click();
	}

	public String retriveInvalidEmailWarningMessage()
	{
		String  actualWarningMessage = inValidEmailWarningMessage.getText();
		return actualWarningMessage;
	}
}
