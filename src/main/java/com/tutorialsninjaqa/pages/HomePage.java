package com.tutorialsninjaqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver; 
	
	@FindBy (xpath = "//span[contains(text(), 'My Account')]")
	private WebElement myAccountDropMenu; 
	
	
	@FindBy (linkText = "Login")
	private WebElement loginOption; 
	
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	public HomePage(WebDriver driver) // creating the constructor and this constructor is call when you create the Object of class i.e HomePage
	{
		this.driver=driver;    // call the global variable into the local/constructor 
		PageFactory.initElements(driver, this); // to avoid refferance state element exception in  selenium we have put this line 
		                                        // this make the connection between the locator and webelements 
		
	}
	
	// Action method 
	public void clickOnMyAccount () {
		myAccountDropMenu.click();
	}
	
	public LogInPage selectLogInOption() {
		loginOption.click();
		// we know that once we click on the Select Log in option we will navigate to the LogIn page 
		// here we will return the object for logInPage 
		return new LogInPage(driver);
	}
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		
		return new RegisterPage(driver);
	}
	
}
