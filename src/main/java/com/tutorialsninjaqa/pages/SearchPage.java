package com.tutorialsninjaqa.pages;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;

	@FindBy(xpath = "//div[@id='search']/input[@name='search']")
	WebElement SerachField;

	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	WebElement SearchButton;

	@FindBy(linkText = "iMac")
	WebElement validSearchProducttext;

	@FindBy(xpath = "(//p)[3]")
	WebElement invalidProductSearchtext;
	
	@FindBy(xpath = "//div[@id='search']/input[@name='search']")
	WebElement multipleProductSearchText;
	
	@FindBy(xpath = "//div[@class='caption']/h4/a")
	WebElement multipleProductText;
	
	@FindBy (xpath = "//a[@id='compare-total']")
	WebElement productCompareLink;

	public SearchPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public String enterValidProductNameInSerachField(String validProdcutNameInSearchField) {
		SerachField.sendKeys(validProdcutNameInSearchField);
		return validProdcutNameInSearchField;
	}

	public void clickOnSearchButton() {
		SearchButton.click();
	}

	public boolean checkValidSearchProducttextdisplaying() {
		boolean validProductSearch = validSearchProducttext.isDisplayed();
		return validProductSearch;
	}

	public String enterInvalidProductIntoSearchField(String invalidProductInSearchField) {
		SerachField.sendKeys(invalidProductInSearchField);
		return invalidProductInSearchField;
	}

	public String retriveInvalidProductSearchtext() {
		String invalidProductSearch = invalidProductSearchtext.getText();
		return invalidProductSearch;

	}
	
	public void enterValidCommonProduct(String commonProduct)
	{
	 multipleProductSearchText.sendKeys(commonProduct);
	}
	
	public String getMultipleProductText()
	{
	String multipleGetText=	multipleProductText.getText();
	return multipleGetText;
	}
	public void clickOnProductCompareLink()
	{
		productCompareLink.click();
	}
	
	
}
