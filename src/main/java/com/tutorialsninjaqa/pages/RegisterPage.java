package com.tutorialsninjaqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;

	@FindBy(id = "input-firstname")
	WebElement firstNameField;

	@FindBy(id = "input-lastname")
	WebElement lastNameField;

	@FindBy(id = "input-email")
	WebElement emailAddressField;

	@FindBy(id = "input-telephone")
	WebElement telephoneField;

	@FindBy(id = "input-password")
	WebElement passwordfield;

	@FindBy(id = "input-confirm")
	WebElement confirmPasswordField;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement termsAndConditionCheckbox;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement ContinueButton;

	@FindBy(xpath = "(//input[@name='newsletter'])[1]")
	WebElement subscribeRadioButton;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement allreadyAccountWarningMessage;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement privacyPolicyAgreeMessage;

	@FindBy(xpath = "(//div[@class='text-danger'])[1]")
	WebElement firstNameWarningMessage;

	@FindBy(xpath = "(//div[@class='text-danger'])[2]")
	WebElement lastNameWarningMessage;

	@FindBy(xpath = "(//div[@class='text-danger'])[3]")
	WebElement emailWarningMessage; 
	
	@FindBy(xpath = "(//div[@class='text-danger'])[4]")
	WebElement phoneNumberWarningMessage;
	
	@FindBy(xpath = "(//div[@class='text-danger'])[5]")
	WebElement passwordsWarningMessage;
			
	public RegisterPage(WebDriver driver) // using bcz avoid stale element reffe exception
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		lastNameField.sendKeys(lastName);
	}

	public void enterEmailAddress(String email) {
		emailAddressField.sendKeys(email);
	}

	public void entertelephoneNumber(String telephone) {
		telephoneField.sendKeys(telephone);
	}

	public void enterPasswords(String password) {
		passwordfield.sendKeys(password);
	}

	public void enterConfirmPasswords(String confirmPass) {
		confirmPasswordField.sendKeys(confirmPass);
	}

	public void clickOnTermsAndConditionCheckbox() {
		termsAndConditionCheckbox.click();
	}

	public void clickOnSubscribeRadioButton() {
		subscribeRadioButton.click();
	}

	public void clickOnContinueButton() {
		ContinueButton.click();
	}

	public String retriveAccountAllreadyExistWarningMessage() {
		String accountExistWarning = allreadyAccountWarningMessage.getText();
		return accountExistWarning;
	}
	
	public String retrivePrivacyPolicyWarningMessage() {
		String privacyPolicyWarningMessage = privacyPolicyAgreeMessage.getText();
		return privacyPolicyWarningMessage;
	}

	public String retriveFirstNameWarningMessage() {
		String getfirstNameWarningMessage = firstNameWarningMessage.getText();
		return getfirstNameWarningMessage;
	}

	public String retriveLastNameWarningMessage() {
		String getlastNameWarningMessage = lastNameWarningMessage.getText();
		return getlastNameWarningMessage;
	}
	public String retriveEmailWarningMessage()
	{
		String getemailwarningMessage = emailWarningMessage.getText();
		return getemailwarningMessage;
	}
	public String retrivePhoneNumberWarningMessage()
	{
		String getPhoneNumberWarningMessage = phoneNumberWarningMessage.getText();
		return getPhoneNumberWarningMessage;
	}
	public String retrivePasswordsWarningMessage() {
		String getPasswordsWarningMessage = passwordsWarningMessage.getText();
		return getPasswordsWarningMessage;
	}
	
}